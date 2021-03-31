package com.example.recruitmenttask.model

import java.io.Serializable

data class Trip(
    val origin: String,
    val originName: String,
    val destination: String,
    val destinationName: String,
    val dates: List<RyanairDate>
)
