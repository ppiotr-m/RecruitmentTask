package com.example.recruitmenttask.view.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.recruitmenttask.R
import com.example.recruitmenttask.databinding.FragmentFlightSearchBinding
import com.example.recruitmenttask.model.Station
import com.example.recruitmenttask.utils.ConstantValues.Companion.MAX_PASSENGER_SETTING_COUNT
import com.example.recruitmenttask.view.MainActivity
import com.example.recruitmenttask.viewmodel.FlightSharedViewModel
import com.example.recruitmenttask.viewmodel.ViewModelInjection
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.atomic.AtomicBoolean

class FlightSearchFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentFlightSearchBinding
    private lateinit var flightSharedViewModel: FlightSharedViewModel   // TODO Rename class to SharedViewModel
    private lateinit var navController: NavController
    private val datePickerDialogMonthOffset = 1
    //  Since when navigation is triggered with observing, this variable prevents navigating when
    //  reentering this fragment from following fragment with back button
    private var shouldNavigate: AtomicBoolean = AtomicBoolean(false)

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

    override fun onResume() {
        super.onResume()

        cleanTopToolbarText()
        hideLoadingOverlay()
    }

    private fun cleanTopToolbarText() {
        (requireActivity() as MainActivity).setToolbarText("")
    }

    private fun setOnClickListener() {
        binding.showCalendarBtn.setOnClickListener {
            showDatePickerDialog()
        }
        binding.searchBtn.setOnClickListener {
            shouldNavigate.set(true)
            showLoadingOverlay()
            flightSharedViewModel.searchForFlights()
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
        flightSharedViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelInjection.provideViewModelFactory()
        ).get(FlightSharedViewModel::class.java)
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
        binding.originStationACTV.setOnItemClickListener { _, view, _, _ ->
            flightSharedViewModel.setOriginStationCode((view as TextView).text.toString())
        }

        binding.destinationStationACTV.setOnItemClickListener { _, view, _, _ ->
            flightSharedViewModel.setDestinationStationCode((view as TextView).text.toString())
        }
    }

    private fun setupObservers() {
        flightSharedViewModel.stations.observe(viewLifecycleOwner, {
            setupAutoCompleteTextViews(it.stations)
            hideLoadingOverlay()
        })
        flightSharedViewModel.date.observe(viewLifecycleOwner, {
            binding.selectedDateTV.text = it.toString()
        })
        flightSharedViewModel.inputErrorOccurred.observe(viewLifecycleOwner, {
            if(it == true) {
                hideLoadingOverlay()
                Toast.makeText(requireContext(), R.string.input_error_msg, Toast.LENGTH_LONG)
                    .show()
            }
        })
        flightSharedViewModel.networkErrorOccurred.observe(viewLifecycleOwner, {
            if(it == true) {
                hideLoadingOverlay()
                Toast.makeText(requireContext(), R.string.network_error_msg, Toast.LENGTH_LONG)
                    .show()
            }
        })
        flightSharedViewModel.flightsData.observe(viewLifecycleOwner, {
            if(shouldNavigate.get() == true) {
                shouldNavigate.set(false)
                navigateToListFragment()
            }
        })
    }

    private fun navigateToListFragment() {
        navController.navigate(R.id.action_flightSearchFragment_to_flightListFragment)
    }

    private fun showDatePickerDialog() {
        DatePickerDialog(
            requireContext(),
            this,
            flightSharedViewModel.date.value!!.year,
            flightSharedViewModel.date.value!!.monthValue - datePickerDialogMonthOffset,
            flightSharedViewModel.date.value!!.dayOfMonth
        ).show()
    }

    private fun initNumberPickers() {
        setInitialValuesForNumberPickers()
        setMaxValuesForNumberPickers()
        setValueChangedListenersForNumberPickers()
    }

    private fun setInitialValuesForNumberPickers() {
        binding.adultsNumberPicker.value = flightSharedViewModel.adultsCount
        binding.teensNumberPicker.value = flightSharedViewModel.teensCount
        binding.childrenNumberPicker.value = flightSharedViewModel.childrenCount
    }

    private fun setMaxValuesForNumberPickers() {
        binding.adultsNumberPicker.maxValue = MAX_PASSENGER_SETTING_COUNT
        binding.teensNumberPicker.maxValue = MAX_PASSENGER_SETTING_COUNT
        binding.childrenNumberPicker.maxValue = MAX_PASSENGER_SETTING_COUNT
    }

    private fun setValueChangedListenersForNumberPickers() {
        binding.adultsNumberPicker.setOnValueChangedListener { _, _, newVal ->
            flightSharedViewModel.adultsCount = newVal
        }
        binding.teensNumberPicker.setOnValueChangedListener { _, _, newVal ->
            flightSharedViewModel.teensCount = newVal
        }
        binding.childrenNumberPicker.setOnValueChangedListener { _, _, newVal ->
            flightSharedViewModel.childrenCount = newVal
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        flightSharedViewModel.setDate(year, month + datePickerDialogMonthOffset, dayOfMonth)
    }

    private fun showLoadingOverlay() {
        binding.loadingMessageOverlay.visibility = View.VISIBLE
        binding.searchFormContainer.visibility = View.GONE
    }

    private fun hideLoadingOverlay() {
        binding.loadingMessageOverlay.visibility = View.GONE
        binding.searchFormContainer.visibility = View.VISIBLE
    }
}