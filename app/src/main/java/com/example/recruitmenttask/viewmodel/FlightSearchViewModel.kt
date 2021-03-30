package com.example.recruitmenttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruitmenttask.model.StationsResponse
import com.example.recruitmenttask.repository.Repository
import kotlinx.coroutines.launch

class FlightSearchViewModel(private val repository: Repository) : ViewModel() {

    private val _stations = MutableLiveData<StationsResponse>()
    val stations: LiveData<StationsResponse> = _stations

    init {
        viewModelScope.launch {
            _stations.value = repository.getStations()
        }
    }
}