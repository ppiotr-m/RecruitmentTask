package com.example.recruitmenttask.model

import com.example.recruitmenttask.model.local.FlightDetailModel
import com.example.recruitmenttask.model.local.FlightListModel

data class FlightsResponse(
    val termsOfUse: String,
    val currency: String,
    val currPrecision: Int,
    val trips: List<Trip>,
    val serverTimeUTC: String   //  TODO Probably change to LocalDateTime
) {
    fun createFlightDetailModelForFlightIndex(index: Int): FlightDetailModel? {
        return if(index < trips[0].dates[0].flights.size && index >= 0) {
            FlightDetailModel(
                trips[0].origin,
                trips[0].destination,
                trips[0].dates[0].flights[index].infantsLeft,
                trips[0].dates[0].flights[index].regularFare.fareClass,
                trips[0].dates[0].flights[index].regularFare.fares[0].discountInPercent
            )
        } else {
            null
        }
    }

    fun toFlightListModel(): FlightListModel {
        return FlightListModel(trips[0].dates[0].dateOut, currency, trips[0].dates[0].flights)
    }
}
