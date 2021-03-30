package com.example.recruitmenttask.model

data class RegularFare(
    val fareKey: String,
    val fareClass: String,
    val fares: List<Fare>
) {
    override fun toString(): String {
        return "RegularFare(fareKey='$fareKey', fareClass='$fareClass', fares=$fares)"
    }
}
