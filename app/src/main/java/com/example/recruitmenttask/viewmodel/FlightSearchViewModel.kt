package com.example.recruitmenttask.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruitmenttask.exceptions.FailedRemoteResponse
import com.example.recruitmenttask.model.FlightsRequest
import com.example.recruitmenttask.model.FlightsResponse
import com.example.recruitmenttask.model.StationsResponse
import com.example.recruitmenttask.repository.Repository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FlightSearchViewModel(private val repository: Repository) : ViewModel() {

    private val _stations = MutableLiveData<StationsResponse>()
    val stations: LiveData<StationsResponse> = _stations

    private val _date = MutableLiveData(LocalDate.now())
    val date: LiveData<LocalDate> = _date

    private val _networkErrorOccurred = MutableLiveData(false)
    val networkErrorOccurred: LiveData<Boolean> = _networkErrorOccurred

    private val _inputErrorOccurred = MutableLiveData(false)
    val inputErrorOccurred = _inputErrorOccurred

    private val _flightsData = MutableLiveData<FlightsResponse>()
    val flightsData: LiveData<FlightsResponse> = _flightsData

    var adultsCount = 0
    var teensCount = 0
    var childrenCount = 0

    var originStationIndex = -1
    var destinationStationIndex = -1

    init {
        getStationsList()
    }

    private fun getStationsList() {
        viewModelScope.launch {
            _stations.value = repository.getStations()
        }
    }

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        _date.value = LocalDate.of(year, month, dayOfMonth)
    }

    fun searchForFlights() {
        if(validateInput()) {
//            val originCode = stations.value!!.stations[originStationIndex].code
//            val destinationCode = stations.value!!.stations[destinationStationIndex].code

            val originCode = "WRO"
            val destinationCode = "DUB"

            val flightsRequest = FlightsRequest(
                date.value.toString(),
                false,
                originCode,
                destinationCode,
                0,
                0,
                0,
                0,
                adultsCount,
                teensCount,
                childrenCount
            )
            viewModelScope.launch {
                try {
                    _flightsData.value = repository.getFlights(flightsRequest)
                } catch(ex: FailedRemoteResponse) {
                    _networkErrorOccurred.value = true
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        val isInputValid = ((originStationIndex >= 0 && destinationStationIndex >= 0)
                && (date.value!!.isAfter(LocalDate.now()) || date.value!!.isEqual(LocalDate.now()))
                && ((adultsCount + teensCount + childrenCount) > 0))
        return if(isInputValid) {
            isInputValid
        } else {
            _inputErrorOccurred.value = true
            isInputValid
        }
    }
}