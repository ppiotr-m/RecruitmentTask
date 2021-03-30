package com.example.recruitmenttask.viewmodel

import android.util.Log
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

    private var originStationCode: String? = null
    private var destinationStationCode: String? = null

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
            val flightsRequest = FlightsRequest(
                date.value.toString(),
                false,
                originStationCode!!,
                destinationStationCode!!,
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
        val isInputValid = ((originStationCode != null && destinationStationCode != null)
//                && (date.value!!.isAfter(LocalDate.now()) || date.value!!.isEqual(LocalDate.now()))
                && ((adultsCount + teensCount + childrenCount) > 0))
        return if(isInputValid) {
            isInputValid
        } else {
            _inputErrorOccurred.value = true
            isInputValid
        }
    }

    fun setOriginStationCode(originStationCode: String) {
        this.originStationCode = getStationCodeFromStationSelectionString(originStationCode)
    }

    fun setDestinationStationCode(destinationStationCode: String) {
        this.destinationStationCode = getStationCodeFromStationSelectionString(destinationStationCode)
    }

    fun getStationCodeFromStationSelectionString(stationSelectionString: String): String {
        Log.d("flight", "Selection String: " + stationSelectionString)

        val indextOfLastSpace = stationSelectionString.indexOfLast { it == ' ' }

        Log.d("flight", "Index of last space: " + indextOfLastSpace)

        return stationSelectionString.substring(indextOfLastSpace + 2, indextOfLastSpace + 5)
    }
}