package com.example.recruitmenttask.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmenttask.R
import com.example.recruitmenttask.databinding.FragmentFlightListBinding
import com.example.recruitmenttask.utils.ConstantValues.Companion.INITIAL_MAX_PRICE
import com.example.recruitmenttask.view.MainActivity
import com.example.recruitmenttask.view.adapters.FlightListAdapter
import com.example.recruitmenttask.view.interfaces.FlightListElementOnClickListener
import com.example.recruitmenttask.viewmodel.FlightSharedViewModel
import com.example.recruitmenttask.viewmodel.ViewModelInjection
import java.util.concurrent.atomic.AtomicBoolean

class FlightListFragment : Fragment(), FlightListElementOnClickListener {

    private lateinit var binding: FragmentFlightListBinding
    private lateinit var flightSharedViewModel: FlightSharedViewModel
    private lateinit var navController: NavController
    private val isTopToolbarTextSet = AtomicBoolean(false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightListBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        navController = findNavController()
        initViewModel()
        setupObservers()
        setInitialSliderValue()
        setupRecyclerView()
        setSliderValueChangeListener()
    }

    private fun setupRecyclerView() {
        binding.flightsList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setInitialSliderValue() {
        binding.priceSlider.value = INITIAL_MAX_PRICE
    }

    private fun setupObservers() {
        flightSharedViewModel.flightsData.observe(viewLifecycleOwner, {
            //  TODO Change it to updating data instead of creating new adapter
            if(it != null) {
                setToolbarText()

                if(binding.flightsList.adapter == null) {
                    binding.flightsList.adapter = FlightListAdapter(it, this)
                } else {
                    val adapter = binding.flightsList.adapter
                    adapter!!.notifyDataSetChanged()
                }

                if(it.flightsList.isEmpty()){
                    showNoFlightsOverlay()
                } else {
                    hideNoFlightsOverlay()
                }
            }
        })
    }

    private fun setToolbarText() {
        if(flightSharedViewModel.flightsData.value != null && !isTopToolbarTextSet.get()) {
            isTopToolbarTextSet.set(true)
            val origin = flightSharedViewModel.flightsData.value!!.originName
            val destination = flightSharedViewModel.flightsData.value!!.destinationName
            (requireActivity() as MainActivity).setToolbarText("$origin -> $destination")
        }
    }

    private fun initViewModel() {
        flightSharedViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelInjection.provideViewModelFactory()
        ).get(FlightSharedViewModel::class.java)
    }

    override fun onItemClicked(itemPosition: Int) {
        val detailModelData =
            flightSharedViewModel.flightsData.value!!.createFlightDetailModelForFlightIndex(
                itemPosition
            )
        if (detailModelData != null) {
            val directions =
                FlightListFragmentDirections.actionFlightListFragmentToFlightDetailFragment(
                    detailModelData
                )
            navController.navigate(directions)
        } else {
            Toast.makeText(requireContext(), R.string.toast_error_message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showNoFlightsOverlay() {
        binding.noFlightsOverlayMessageView.visibility = View.VISIBLE
    }

    private fun hideNoFlightsOverlay() {
        binding.noFlightsOverlayMessageView.visibility = View.GONE
    }

    private fun setSliderValueChangeListener() {
        binding.priceSlider.addOnChangeListener { slider, value, fromUser ->
            flightSharedViewModel.setMaxPrice(value)
        }
    }
}