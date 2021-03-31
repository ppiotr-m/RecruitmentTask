package com.example.recruitmenttask.model

data class FlightsRequest(
    val dateOut: String,
    val roundTrip: Boolean = false,
    val origin: String,
    val destination: String,
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
        map.put("adt", adt.toString())
        map.put("teen", teen.toString())
        map.put("chd", chd.toString())
        map.put("ToUs", "AGREED")

        return map
    }
}
