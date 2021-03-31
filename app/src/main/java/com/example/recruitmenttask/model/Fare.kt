package com.example.recruitmenttask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Fare(
    val type: String,
    val amount: Double,
    val count: Int,
    val hasDiscount: Boolean,
    val publishedFare: Double,
    val discountInPercent: Int,
    val hasPromoDiscount: Boolean,
    val discountAmount: Double,
    @SerializedName("hasBogof")
    val hasBogOf: Boolean
)
