package com.example.recruitmenttask.model

import java.io.Serializable

data class FlightsResponse(
    val termsOfUse: String,
    val currency: String,
    val currPrecision: Int,
    val trips: List<Trip>,
    val serverTimeUTC: String   //  TODO Probably change to LocalDateTime
) : Serializable
