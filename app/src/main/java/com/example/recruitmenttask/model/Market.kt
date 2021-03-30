package com.example.recruitmenttask.model

data class Market(val code: String, val group: String?) {
    override fun toString(): String {
        return "Market(code='$code', group=$group)"
    }
}
