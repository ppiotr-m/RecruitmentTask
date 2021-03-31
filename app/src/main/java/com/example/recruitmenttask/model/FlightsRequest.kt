package com.example.recruitmenttask.model

data class FlightsRequest(
    val dateOut: String,
//    val dateIn:String,
    val roundTrip: Boolean = false,
    val origin: String,
    val destination: String,
    val flexDaysOut: Int,
    val flexDaysIn: Int,
    val flexDaysBeforeOut: Int,
    val flexDaysBeforeIn: Int,
    val adt: Int,
    val teen: Int,
    val chd: Int
    ) {

    fun getQueryMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()

        map.put("dateout", dateOut)
        map.put("roundtrip", roundTrip.toString())
        map.put("origin", origin)
        map.put("destination", destination)
        map.put("flexdaysout", flexDaysOut.toString())
        map.put("flexdaysin", flexDaysIn.toString())
        map.put("flexdaysbeforeout", flexDaysBeforeOut.toString())
        map.put("flexdaysbeforein", flexDaysBeforeIn.toString())
        map.put("adt", adt.toString())
        map.put("teen", teen.toString())
        map.put("chd", chd.toString())
        map.put("ToUs", "AGREED")

        return map
    }
}
