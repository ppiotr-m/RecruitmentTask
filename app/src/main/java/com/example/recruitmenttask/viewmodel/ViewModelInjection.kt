package com.example.recruitmenttask.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.recruitmenttask.remote.FlightAPI
import com.example.recruitmenttask.remote.FlightServiceGenerator
import com.example.recruitmenttask.repository.Repository

object ViewModelInjection {
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        //  TODO Change to HILT or improve this
        return FlightViewModelFactory(
            Repository(FlightServiceGenerator.getRetrofitInstance().create(FlightAPI::class.java))
        )
    }
}