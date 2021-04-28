package com.example.recruitmenttask.utils

class Event {

    var hasBeenHandled = false
        private set // Allow external read but not write

    fun getContentIfNotHandled(): Unit? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
        }
    }
}