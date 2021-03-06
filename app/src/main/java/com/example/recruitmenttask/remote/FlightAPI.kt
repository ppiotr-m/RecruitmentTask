package com.example.recruitmenttask.remote

import com.example.recruitmenttask.model.FlightsResponse
import com.example.recruitmenttask.model.StationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FlightAPI {

    @GET("/static/stations.json")
    suspend fun fetchStationsList(): Response<StationsResponse>

    @GET("https://www.ryanair.com/api/booking/v4/en-gb/Availability")
    suspend fun fetchFlightsList(@QueryMap queryMap: Map<String, String>): Response<FlightsResponse>
}