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
import com.example.recruitmenttask.view.MainActivity
import com.example.recruitmenttask.view.elements.FlightListAdapter
import com.example.recruitmenttask.view.interfaces.FlightListElementOnClickListener
import com.example.recruitmenttask.viewmodel.FlightSharedViewModel
import com.example.recruitmenttask.viewmodel.ViewModelInjection

class FlightListFragment : Fragment(), FlightListElementOnClickListener {

    private lateinit var binding: FragmentFlightListBinding
    private lateinit var flightSharedViewModel: FlightSharedViewModel
    private lateinit var navController: NavController

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
        setToolbarText()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if(flightSharedViewModel.flightsData.value!!.trips.isEmpty()) {
            showNoFlightsOverlay()
            return
        }
        binding.flightsList.layoutManager = LinearLayoutManager(requireContext())
        binding.flightsList.adapter =
            FlightListAdapter(flightSharedViewModel.flightsData.value!!, this)
    }

    private fun setToolbarText() {
        val origin = flightSharedViewModel.flightsData.value!!.trips[0].originName
        val destination = flightSharedViewModel.flightsData.value!!.trips[0].destinationName
        (requireActivity() as MainActivity).setToolbarText("$origin -> $destination")
    }

    private fun initViewModel() {
        flightSharedViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelInjection.provideViewModelFactory(requireActivity().application)
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
}