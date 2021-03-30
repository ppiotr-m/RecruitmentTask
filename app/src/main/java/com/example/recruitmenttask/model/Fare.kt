package com.example.recruitmenttask.model

data class Fare(
    val type: String,
    val amount: Double,
    val count: Int,
    val hasDiscount: Boolean,
    val publishedFare: Double,
    val discountInPercent: Double,
    val hasPromoDiscount: Boolean
)
