package com.example.recruitmenttask.model

data class FlightsResponse(
    val termsOfUse: String,
    val currency: String,
    val currPrecision: Int,
    val trips: List<Trip>,
    val serverTimeUTC: String   //  TODO Probably change to LocalDateTime
) {
    override fun toString(): String {
        return "FlightsResponse(termsOfUse='$termsOfUse', currency='$currency', currPrecision=$currPrecision, trips=$trips, serverTimeUTC='$serverTimeUTC')"
    }
}
