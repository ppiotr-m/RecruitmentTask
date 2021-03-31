package com.example.recruitmenttask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Flight(
    val faresLeft: Int,
    val flightKey: String,
    val infantsLeft: Int,
    val regularFare: RegularFare,
    val operatedBy: String,
    val segments: List<Segment>,
    val flightNumber: String,
    val time: List<String>,
    val timeUTC: List<String>,
    val duration: String
)
