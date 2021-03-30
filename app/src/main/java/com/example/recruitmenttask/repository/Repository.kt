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

//@InstallIn(ActivityComponent::class) // TODO Inny komponent
//@Module
class Repository
//@Inject constructor
    (private val flightService: FlightAPI) {

    suspend fun getStations(): StationsResponse {
        val response = flightService.fetchStationsList()

        if(response.isSuccessful) {
            return response.body()!!
        }
        throw FailedRemoteResponse()
    }

    suspend fun getFlights(flightsRequest: FlightsRequest): FlightsResponse {
        val response = flightService.fetchFlightsList(flightsRequest.getQueryMap())

        Log.d("flight", "After fetching flights: " + response)

        if(response.isSuccessful) {
            return response.body()!!
        }

        Log.d("flight", "Response: " + response.code())

        throw FailedRemoteResponse()
    }
}