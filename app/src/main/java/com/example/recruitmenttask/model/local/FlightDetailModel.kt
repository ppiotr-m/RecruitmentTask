package com.example.recruitmenttask.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class FlightDetailModel(
    val origin: String,
    val destination: String,
    val infantsLeft: Int,
    val fareClass: String,
    val discountInPercent: Int
) : Serializable
