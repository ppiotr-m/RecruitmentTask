package com.example.recruitmenttask.model

import java.io.Serializable
import java.time.Duration

data class Flight(
    val faresLeft: Int,
    val flightKey: String,
    val infantsLeft: Int,
    val regularFare: RegularFare,
    val segments: List<Segment>,
    val flightNumber: String,
    val time: List<String>,     //  TODO Change to LocalDateTime
    val timeUTC: List<String>,  //  TODO Change to Local DateTime
    val duration: Duration
) : Serializable
