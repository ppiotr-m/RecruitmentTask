package com.example.recruitmenttask.model

data class Flight(
    val faresLeft: Int,
    val flightKey: String,
    val infantsLeft: Int,
    val regularFare: RegularFare,
    val operatedBy: String,
    val segments: List<Segment>,
    val flightNumber: String,
    val time: List<String>,     //  TODO Change to LocalDateTime
    val timeUTC: List<String>,  //  TODO Change to Local DateTime
    val duration: String
)
