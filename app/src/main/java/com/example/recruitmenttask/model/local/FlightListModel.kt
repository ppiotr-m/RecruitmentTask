package com.example.recruitmenttask.model.local

import com.example.recruitmenttask.model.Flight

data class FlightListModel(val date: String, val currency: String, val flightsList: List<Flight>)