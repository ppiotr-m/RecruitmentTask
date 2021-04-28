package com.example.recruitmenttask.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recruitmenttask.repository.Repository

class FlightViewModelFactory(
    private val flightRepository: Repository,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlightSharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlightSharedViewModel(application, flightRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}