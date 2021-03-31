package com.example.recruitmenttask.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmenttask.databinding.FragmentFlightListBinding
import com.example.recruitmenttask.model.Flight
import com.example.recruitmenttask.view.MainActivity
import com.example.recruitmenttask.view.elements.FlightListAdapter
import com.example.recruitmenttask.view.interfaces.FlightListElementOnClickListener
import com.example.recruitmenttask.viewmodel.FlightSearchViewModel
import com.example.recruitmenttask.viewmodel.ViewModelInjection

class FlightListFragment : Fragment(), FlightListElementOnClickListener {

    private lateinit var binding: FragmentFlightListBinding
    private lateinit var flightSearchViewModel: FlightSearchViewModel

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

//        binding.lifecycleOwner = viewLifecycleOwner

        init()
    }

    private fun init() {
        initViewModel()
        setToolbarText()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.flightsList.layoutManager = LinearLayoutManager(requireContext())
        binding.flightsList.adapter = FlightListAdapter(flightSearchViewModel.flightsData.value!!)
    }

    private fun setToolbarText() {
        val origin = flightSearchViewModel.flightsData.value!!.trips[0].originName
        val destination = flightSearchViewModel.flightsData.value!!.trips[0].destinationName
        (requireActivity() as MainActivity).setToolbarText("$origin -> $destination")
    }

    private fun initViewModel() {
        flightSearchViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelInjection.provideViewModelFactory()
        ).get(FlightSearchViewModel::class.java)
    }

    override fun onItemClicked(itemPosition: Int) {

    }
}