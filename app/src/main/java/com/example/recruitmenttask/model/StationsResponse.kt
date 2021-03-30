package com.example.recruitmenttask.model

data class StationsResponse (
    val stations: List<Station>
    ) {
    override fun toString(): String {
        return "StationsResponse(stations=$stations)"
    }
}