package com.example.recruitmenttask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class RegularFare(
    val fareKey: String,
    val fareClass: String,
    val fares: List<Fare>
)
