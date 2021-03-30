package com.example.recruitmenttask.model

data class Trip(
    val origin: String,
    val originName: String,
    val destination: String,
    val destinationName: String,
    val dates: List<RyanairDate>,
)
