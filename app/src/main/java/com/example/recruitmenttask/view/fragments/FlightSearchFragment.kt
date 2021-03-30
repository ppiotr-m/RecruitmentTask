package com.example.recruitmenttask.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.recruitmenttask.R
import com.example.recruitmenttask.databinding.FragmentFlightSearchBinding
import com.example.recruitmenttask.model.Station
import com.example.recruitmenttask.viewmodel.FlightSearchViewModel
import com.example.recruitmenttask.viewmodel.ViewModelInjection

class FlightSearchFragment : Fragment() {

    private lateinit var binding: FragmentFlightSearchBinding
    private lateinit var flightSearchViewModel: FlightSearchViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightSearchBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        init()
    }

    private fun setOnClickListener() {
        binding.searchBtn.setOnClickListener {
            navigateToListFragment()
        }
    }

    private fun init() {
        navController = findNavController()
        setOnClickListener()
        initViewModel()
        setupObservers()
    }

    private fun initViewModel() {
        flightSearchViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelInjection.provideViewModelFactory()
        ).get(FlightSearchViewModel::class.java)
    }

    private fun setupAutoCompleteTextViews(stations: List<Station>) {
        binding.originStationACTV.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                stations
            )
        )
        binding.destinationStationACTV.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                stations
            )
        )
    }

    private fun setupObservers() {
        flightSearchViewModel.stations.observe(viewLifecycleOwner, {
            Log.d("flight", "Result: " + it.stations[1].toString())
            setupAutoCompleteTextViews(it.stations)
        })
        flightSearchViewModel.date.observe(viewLifecycleOwner, {
            binding.selectedDateTV.text = it.toString()
        })
    }

    private fun navigateToListFragment() {
        navController.navigate(R.id.action_flightSearchFragment_to_flightListFragment)
    }

}