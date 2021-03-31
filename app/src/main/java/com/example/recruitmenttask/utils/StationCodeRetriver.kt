package com.example.recruitmenttask.utils

class StationCodeRetriver {
    fun getStationCodeFromStationSelectionString(stationSelectionString: String): String {
        val firstCodeLetterOffset = 2
        val lastCodeLetterOffset = 5
        val indexOfLastSpace = stationSelectionString.indexOfLast { it == ' ' }

        return stationSelectionString.substring(indexOfLastSpace + firstCodeLetterOffset, indexOfLastSpace + lastCodeLetterOffset)
    }
}