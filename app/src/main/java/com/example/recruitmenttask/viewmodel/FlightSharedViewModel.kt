package com.example.recruitmenttask.viewmodel

import androidx.lifecycle.*
import com.example.recruitmenttask.exceptions.FailedRemoteResponse
import com.example.recruitmenttask.model.FlightsRequest
import com.example.recruitmenttask.model.StationsResponse
import com.example.recruitmenttask.model.local.FlightListModel
import com.example.recruitmenttask.repository.Repository
import com.example.recruitmenttask.utils.StationCodeRetriver
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.stream.Collectors

class FlightSharedViewModel(private val repository: Repository) : ViewModel() {

    private val _stations = MutableLiveData<StationsResponse>()
    val stations: LiveData<StationsResponse> = _stations

    private val _date = MutableLiveData(LocalDate.now())
    val date: LiveData<LocalDate> = _date

    private val _networkErrorOccurred = MutableLiveData(false)
    val networkErrorOccurred: LiveData<Boolean> = _networkErrorOccurred

    private val _inputErrorOccurred = MutableLiveData(false)
    val inputErrorOccurred = _inputErrorOccurred

    private val _maxPrice = MutableLiveData(0F)
    val maxPrice: LiveData<Float> = _maxPrice

    private val _flightsDataReceived = MutableLiveData(false)
    val flightsDataReceived: LiveData<Boolean> = _flightsDataReceived

    private val _flightsData = MutableLiveData<FlightListModel>()
    val flightsData: LiveData<FlightListModel?> = Transformations.map(maxPrice) {
            maxPrice -> getFlightsCheaperThanGivenPrice(maxPrice)
    }

    fun setMaxPrice(maxPrice: Float) {
        _maxPrice.value = maxPrice
    }

    var adultsCount = 0
    var teensCount = 0
    var childrenCount = 0

    private val stationCodeRetriver = StationCodeRetriver()
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

    private fun getFlightsCheaperThanGivenPrice(maxPrice: Float): FlightListModel? {
        if(_flightsData.value != null) {
            val filteredList = _flightsData.value!!.flightsList
                .stream()
                .filter { flight -> flight.regularFare.fares[0].amount <= maxPrice }
                .collect(Collectors.toList())
            return _flightsData.value!!.copy(flightsList = filteredList)
        } else {
            return null
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
                adultsCount,
                teensCount,
                childrenCount
            )
            viewModelScope.launch {
                try {
                    _flightsData.value = repository.getFlights(flightsRequest).toFlightListModel()
                    _flightsDataReceived.value = true
                } catch(ex: FailedRemoteResponse) {
                    _networkErrorOccurred.value = true
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        val isInputValid = ((originStationCode != null && destinationStationCode != null)
                && (date.value!!.isAfter(LocalDate.now()) || date.value!!.isEqual(LocalDate.now()))
                && ((adultsCount + teensCount + childrenCount) > 0))
        return if(isInputValid) {
            isInputValid
        } else {
            _inputErrorOccurred.value = true
            isInputValid
        }
    }

    fun setOriginStationCode(originStationCode: String) {
        this.originStationCode = stationCodeRetriver.getStationCodeFromStationSelectionString(originStationCode)
    }

    fun setDestinationStationCode(destinationStationCode: String) {
        this.destinationStationCode = stationCodeRetriver.getStationCodeFromStationSelectionString(destinationStationCode)
    }
}