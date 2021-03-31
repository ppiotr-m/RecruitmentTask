package com.example.recruitmenttask.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recruitmenttask.databinding.FragmentFlightListBinding
import com.example.recruitmenttask.databinding.FragmentFlightSearchBinding
import com.example.recruitmenttask.view.elements.FlightListAdapter
import com.example.recruitmenttask.viewmodel.FlightSearchViewModel
import com.example.recruitmenttask.viewmodel.ViewModelInjection

class FlightListFragment : Fragment() {

    private lateinit var binding: FragmentFlightListBinding
//    private lateinit var flightSearchViewModel: FlightSearchViewModel

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
//        initViewModel()
//        setupRecyclerView()
    }

//    private fun setupRecyclerView() {
//        binding.flightsList.adapter = FlightListAdapter(
//            flightSearchViewModel.flightsData.value!!.trips[0].dates[0].flights,
//            flightSearchViewModel.flightsData.value!!.trips[0].dates[0].dateOut
//        )
//    }
//
//    private fun initViewModel() {
//        flightSearchViewModel = ViewModelProvider(
//            requireActivity(),
//            ViewModelInjection.provideViewModelFactory()
//        ).get(FlightSearchViewModel::class.java)
//    }
}