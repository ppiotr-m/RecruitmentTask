package com.example.recruitmenttask.repository

import com.example.recruitmenttask.exceptions.FailedRemoteResponse
import com.example.recruitmenttask.model.StationsResponse
import com.example.recruitmenttask.remote.FlightAPI
import com.example.recruitmenttask.remote.FlightServiceGenerator

class Repository(private val flightService: FlightAPI) {

    suspend fun getStations(): StationsResponse {
        val response = flightService.fetchStationsList()

        if(response.isSuccessful){
            return response.body()!!
        }
        throw FailedRemoteResponse()
    }
}