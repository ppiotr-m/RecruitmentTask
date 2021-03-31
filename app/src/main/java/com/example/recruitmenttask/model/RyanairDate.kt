package com.example.recruitmenttask.model

import java.io.Serializable

data class RyanairDate(
    val dateOut: String,    //  TODO Change to LocalDateTime
    val flights: List<Flight>
) : Serializable