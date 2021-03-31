package com.example.recruitmenttask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recruitmenttask.repository.Repository

class FlightViewModelFactory(
    private val flightRepository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlightSharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlightSharedViewModel(flightRepository) as T
        } else if (modelClass.isAssignableFrom(FlightListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlightListViewModel(flightRepository) as T
        } else if (modelClass.isAssignableFrom(FlightDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlightDetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}