package com.example.recruitmenttask.model

data class RyanairDate(
    val dateOut: String,    //  TODO Change to LocalDateTime
    val flights: List<Flight>
)
{
    override fun toString(): String {
        return "RyanairDate(dateOut='$dateOut', flights=$flights)"
    }
}
