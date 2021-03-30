package com.example.recruitmenttask.repository

import android.util.Log
import com.example.recruitmenttask.exceptions.FailedRemoteResponse
import com.example.recruitmenttask.model.FlightsRequest
import com.example.recruitmenttask.model.FlightsResponse
import com.example.recruitmenttask.model.StationsResponse
import com.example.recruitmenttask.remote.FlightAPI
import com.example.recruitmenttask.remote.FlightServiceGenerator

class Repository(private val flightService: FlightAPI) {

    suspend fun getStations(): StationsResponse {
        val response = flightService.fetchStationsList()

        if(response.isSuccessful) {
            return response.body()!!
        }
        throw FailedRemoteResponse()
    }

    suspend fun getFlights(flightsRequest: FlightsRequest): FlightsResponse {
        val response = flightService.fetchFlightsList(flightsRequest.getQueryMap())

        if(response.isSuccessful) {
            return response.body()!!
        }

        Log.d("flight", "Response:\n" + response.code() + "\n" + response.errorBody().toString())

        throw FailedRemoteResponse()
    }
}