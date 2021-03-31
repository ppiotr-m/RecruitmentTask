package com.example.recruitmenttask.model

data class Station(
    val code: String,
    val name: String,
    val alternateName: String,
    val alias: List<String>,
    val countryCode: String,
    val countryName: String,
    val countryAlias: String?,
    val countryGroupCode: String,
    val countryGroupName: String,
    val timeZoneCode: String,
    val latitude: String,
    val longitude: String,
    val mobileBoardingPass: Boolean,
    val markets: List<Market>,
    val notices: String?
) {

    override fun toString(): String {
        return "$name ($code)"
    }
}