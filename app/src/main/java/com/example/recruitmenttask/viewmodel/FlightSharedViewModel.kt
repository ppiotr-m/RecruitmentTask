package com.example.recruitmenttask.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.example.recruitmenttask.exceptions.FailedRemoteResponse
import com.example.recruitmenttask.model.FlightsRequest
import com.example.recruitmenttask.model.FlightsResponse
import com.example.recruitmenttask.model.StationsResponse
import com.example.recruitmenttask.repository.Repository
import com.example.recruitmenttask.utils.Event
import com.example.recruitmenttask.utils.StationCodeRetriver
import kotlinx.coroutines.launch
import java.time.LocalDate

class FlightSharedViewModel(androidApplication: Application, private val repository: Repository) :
    AndroidViewModel(androidApplication) {

    private val _stations = MutableLiveData<StationsResponse>()
    val stations: LiveData<StationsResponse> = _stations

    private val _date = MutableLiveData(LocalDate.now())
    val date: LiveData<LocalDate> = _date

    private val _networkErrorOccurred = MutableLiveData<Event>()
    val networkErrorOccurred: LiveData<Event> = _networkErrorOccurred

    private val _noNetworkConnectionOnEntry = MutableLiveData<Event>()
    val noNetworkConnectionOnEntry: LiveData<Event> = _noNetworkConnectionOnEntry

    private val _noNetworkConnection = MutableLiveData<Event>()
    val noNetworkConnection: LiveData<Event> = _noNetworkConnection

    private val _inputErrorOccurred = MutableLiveData<Event>()
    val inputErrorOccurred: LiveData<Event> = _inputErrorOccurred

    private val _navigateToFlightsList = MutableLiveData<Event>()
    val navigateToFlightsList: LiveData<Event> = _navigateToFlightsList

    private val _dataFromRemoteIsLoading = MutableLiveData<Event>()
    val dataFromRemoteIsLoading: LiveData<Event> = _dataFromRemoteIsLoading

    private val _flightsData = MutableLiveData<FlightsResponse>()
    val flightsData: LiveData<FlightsResponse> = _flightsData

    var adultsCount = 0
    var teensCount = 0
    var childrenCount = 0

    private val stationCodeRetriver = StationCodeRetriver()
    private var originStationCode: String? = null
    private var destinationStationCode: String? = null

    fun init() {
        if (checkInternetConnection()) {
            getStationsList()
        } else {
            _noNetworkConnectionOnEntry.value = Event()
        }
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>()
            .applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    private fun notifyThereIsNoInternetConnection() {
        _noNetworkConnection.value = Event()
    }

    private fun getStationsList() {
        viewModelScope.launch {
            _stations.value = repository.getStations()
        }

    }

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        _date.value = LocalDate.of(year, month, dayOfMonth)
    }

    fun getYear(): Int {
        return date.value!!.year
    }

    fun getMonth(): Int {
        return date.value!!.monthValue
    }

    fun getDay(): Int {
        return date.value!!.dayOfMonth
    }

    fun searchForFlights() {
        if(!checkInternetConnection()) {
            _noNetworkConnection.value = Event()
            return
        }
        _dataFromRemoteIsLoading.value = Event()
        if (validateInput()) {
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
                    _flightsData.value = repository.getFlights(flightsRequest)
                    _navigateToFlightsList.value = Event()
                } catch (ex: FailedRemoteResponse) {
                    _networkErrorOccurred.value = Event()
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        val isInputValid = ((originStationCode != null && destinationStationCode != null)
                && (date.value!!.isAfter(LocalDate.now()) || date.value!!.isEqual(LocalDate.now()))
                && ((adultsCount + teensCount + childrenCount) > 0))
        return if (isInputValid) {
            isInputValid
        } else {
            _inputErrorOccurred.value = Event()
            isInputValid
        }
    }

    fun setOriginStationCode(originStationCode: String) {
        this.originStationCode =
            stationCodeRetriver.getStationCodeFromStationSelectionString(originStationCode)
    }

    fun setDestinationStationCode(destinationStationCode: String) {
        this.destinationStationCode =
            stationCodeRetriver.getStationCodeFromStationSelectionString(destinationStationCode)
    }
}