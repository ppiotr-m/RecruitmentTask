package com.example.recruitmenttask.model

import java.io.Serializable

data class RegularFare(
    val fareKey: String,
    val fareClass: String,
    val fares: List<Fare>
)
