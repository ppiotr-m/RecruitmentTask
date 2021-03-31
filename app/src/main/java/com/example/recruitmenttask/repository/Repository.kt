package com.example.recruitmenttask.repository

import android.util.Log
import com.example.recruitmenttask.exceptions.FailedRemoteResponse
import com.example.recruitmenttask.model.FlightsRequest
import com.example.recruitmenttask.model.FlightsResponse
import com.example.recruitmenttask.model.StationsResponse
import com.example.recruitmenttask.remote.FlightAPI
import com.example.recruitmenttask.remote.FlightServiceGenerator
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject


class Repository(private val flightService: FlightAPI) {

    suspend fun getStations(): StationsResponse {
        val response = flightService.fetchStationsList()

        if (response.isSuccessful) {
            return response.body()!!
        }

        throw FailedRemoteResponse("HTTP request unsuccessful, code: ${response.code()}, message: ${response.message()}")
    }

    suspend fun getFlights(flightsRequest: FlightsRequest): FlightsResponse {
        val response = flightService.fetchFlightsList(flightsRequest.getQueryMap())

        Log.d("flight", "Flights response, size:\n" + response.raw().toString())

        if (response.isSuccessful) {
            return response.body()!!
        }

        throw FailedRemoteResponse("HTTP request unsuccessful, code: ${response.code()}, message: ${response.message()}")
    }
}