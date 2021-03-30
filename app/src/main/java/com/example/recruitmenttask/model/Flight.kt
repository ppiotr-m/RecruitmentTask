package com.example.recruitmenttask.model

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
) {
    override fun toString(): String {
        return "Flight(faresLeft=$faresLeft, flightKey='$flightKey', infantsLeft=$infantsLeft, regularFare=$regularFare, segments=$segments, flightNumber='$flightNumber', time=$time, timeUTC=$timeUTC, duration=$duration)"
    }
}
