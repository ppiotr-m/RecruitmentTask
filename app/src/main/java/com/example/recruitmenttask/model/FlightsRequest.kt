package com.example.recruitmenttask.model

data class FlightsRequest(
    val dateOut: String,
    val dateIn:String,
    val roundTrip: Boolean = false,
    val origin: String,
    val destination: String,
    val flexDaysOut: Int,
    val flexDaysIn: Int,
    val flexDaysBeforeOut: Int,
    val flexDaysBeforeIn: Int,
    val adt: Int,
    val teen: Int,
    val chd: Int,
    val inf: Int,
    val toUs: String,
    val disc: Int
    )
