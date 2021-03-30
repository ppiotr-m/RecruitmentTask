package com.example.recruitmenttask.model

data class Fare(
    val type: String,
    val amount: Double,
    val count: Int,
    val hasDiscount: Boolean,
    val publishedFare: Double,
    val discountInPercent: Double,
    val hasPromoDiscount: Boolean
) {
    override fun toString(): String {
        return "Fare(type='$type', amount=$amount, count=$count, hasDiscount=$hasDiscount, publishedFare=$publishedFare, discountInPercent=$discountInPercent, hasPromoDiscount=$hasPromoDiscount)"
    }
}
