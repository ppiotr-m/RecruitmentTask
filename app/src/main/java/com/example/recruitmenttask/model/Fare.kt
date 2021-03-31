package com.example.recruitmenttask.model

import java.io.Serializable

data class Fare(
    val type: String,
    val amount: Double,
    val count: Int,
    val hasDiscount: Boolean,
    val publishedFare: Double,
    val discountInPercent: Double,
    val hasPromoDiscount: Boolean,
    val discountAmount: Double,
    val hasBogof: Boolean
)
