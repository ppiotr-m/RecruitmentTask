package com.example.recruitmenttask.view.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.recruitmenttask.R
import com.example.recruitmenttask.databinding.FragmentFlightSearchBinding
import com.example.recruitmenttask.model.Station
import com.example.recruitmenttask.utils.ConstantValues.Companion.MAX_PASSENGER_SETTING_COUNT
import com.example.recruitmenttask.viewmodel.FlightSearchViewModel
import com.example.recruitmenttask.viewmodel.ViewModelInjection

class FlightSearchFragment : Fragment(), DatePickerDialog.OnDateSetListener {

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
        binding.showCalendarBtn.setOnClickListener {
            showDatePickerDialog()
        }
        binding.searchBtn.setOnClickListener {
            flightSearchViewModel.searchForFlights()
        }
    }

    private fun init() {
        navController = findNavController()
        setOnClickListener()
        initViewModel()
        setupObservers()
        initNumberPickers()
    }

    private fun initViewModel() {
        flightSearchViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelInjection.provideViewModelFactory()
        ).get(FlightSearchViewModel::class.java)
    }

    private fun setupAutoCompleteTextViews(stations: List<Station>) {
        setAutoCompleteTextViewsAdapters(stations)
        setAutoCompleteTextViewsListeners()
    }

    private fun setAutoCompleteTextViewsAdapters(stations: List<Station>) {
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

    private fun setAutoCompleteTextViewsListeners() {
        binding.originStationACTV.setOnItemClickListener { parent, _, position, _ ->
            flightSearchViewModel.destinationStationIndex = position
        }

        binding.destinationStationACTV.setOnItemClickListener { parent, _, position, _ ->
            Log.d("flight", "Destination position: " + position)
            flightSearchViewModel.originStationIndex = position
        }
    }

    private fun setupObservers() {
        flightSearchViewModel.stations.observe(viewLifecycleOwner, {
            setupAutoCompleteTextViews(it.stations)
        })
        flightSearchViewModel.date.observe(viewLifecycleOwner, {
            binding.selectedDateTV.text = it.toString()
        })
        flightSearchViewModel.inputErrorOccurred.observe(viewLifecycleOwner, {
            if(it == true) {
                Toast.makeText(requireContext(), R.string.input_error_msg, Toast.LENGTH_LONG)
                    .show()
            }
        })
        flightSearchViewModel.networkErrorOccurred.observe(viewLifecycleOwner, {
            if(it == true) {
                Toast.makeText(requireContext(), R.string.network_error_msg, Toast.LENGTH_LONG)
                    .show()
            }
        })
        flightSearchViewModel.flightsData.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Great success!", Toast.LENGTH_LONG).show()
        })
    }

    private fun navigateToListFragment() {
        navController.navigate(R.id.action_flightSearchFragment_to_flightListFragment)
    }

    //  TODO Skips to next month, threw exception on 31 Feb
    private fun showDatePickerDialog() {
        DatePickerDialog(
            requireContext(),
            this,
            flightSearchViewModel.date.value!!.year,
            flightSearchViewModel.date.value!!.monthValue - 1,
            flightSearchViewModel.date.value!!.dayOfMonth
        ).show()
    }

    private fun initNumberPickers() {
        setInitialValuesForNumberPickers()
        setMaxValuesForNumberPickers()
        setValueChangedListenersForNumberPickers()
    }

    private fun setInitialValuesForNumberPickers() {
        binding.adultsNumberPicker.value = flightSearchViewModel.adultsCount
        binding.teensNumberPicker.value = flightSearchViewModel.teensCount
        binding.childrenNumberPicker.value = flightSearchViewModel.childrenCount
    }

    private fun setMaxValuesForNumberPickers() {
        binding.adultsNumberPicker.maxValue = MAX_PASSENGER_SETTING_COUNT
        binding.teensNumberPicker.maxValue = MAX_PASSENGER_SETTING_COUNT
        binding.childrenNumberPicker.maxValue = MAX_PASSENGER_SETTING_COUNT
    }

    private fun setValueChangedListenersForNumberPickers() {
        binding.adultsNumberPicker.setOnValueChangedListener { _, _, newVal ->
            flightSearchViewModel.adultsCount = newVal
        }
        binding.teensNumberPicker.setOnValueChangedListener { _, _, newVal ->
            flightSearchViewModel.teensCount = newVal
        }
        binding.childrenNumberPicker.setOnValueChangedListener { _, _, newVal ->
            flightSearchViewModel.childrenCount = newVal
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        flightSearchViewModel.setDate(year, month + 1, dayOfMonth)
    }
}