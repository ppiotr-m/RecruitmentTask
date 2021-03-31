package com.example.recruitmenttask.model

import com.google.gson.annotations.SerializedName

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
