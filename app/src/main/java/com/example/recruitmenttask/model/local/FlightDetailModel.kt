package com.example.recruitmenttask.model.local

import java.io.Serializable

data class FlightDetailModel(
    val origin: String,
    val destination: String,
    val infantsLeft: Int,
    val fareClass: String,
    val discountInPercent: Int
) : Serializable
