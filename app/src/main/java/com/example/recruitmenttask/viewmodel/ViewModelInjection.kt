package com.example.recruitmenttask.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.recruitmenttask.remote.FlightAPI
import com.example.recruitmenttask.remote.FlightServiceGenerator
import com.example.recruitmenttask.repository.Repository

object ViewModelInjection {
    fun provideViewModelFactory(application: Application): ViewModelProvider.Factory {
        return FlightViewModelFactory(
            Repository(FlightServiceGenerator.getRetrofitInstance().create(FlightAPI::class.java)),
            application
        )
    }
}