package com.example.recruitmenttask.model

data class Segment(
    val segmentNr: Int,
    val origin: String,
    val destination: String,
    val flightNumber: String,
    val time: List<String>,     //  TODO Change to LocalDateTime
    val timeUTC: List<String>,  //  TODO Change to Local DateTime
    val duration: String        //  TODO Change to Duration
)
