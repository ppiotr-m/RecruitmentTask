package com.example.recruitmenttask.model.local

import com.example.recruitmenttask.model.Flight

data class FlightListModel(
    val date: String,
    val currency: String,
    val flightsList: List<Flight>,
    val originName: String,
    val destinationName: String,
    val origin: String,
    val destination: String
) {
    fun createFlightDetailModelForFlightIndex(index: Int): FlightDetailModel? {
        return if (index < flightsList.size && index >= 0) {
            FlightDetailModel(
                origin,
                destination,
                flightsList[index].infantsLeft,
                flightsList[index].regularFare.fareClass,
                flightsList[index].regularFare.fares[0].discountInPercent
            )
        } else {
            null
        }
    }
}