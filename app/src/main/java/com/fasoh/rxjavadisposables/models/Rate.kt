package com.fasoh.rxjavadisposables.models

data class Rate(
    val ask: String,
    val bid: String,
    val close: String,
    val currency: String,
    val high: String,
    val low: String,
    val `open`: String,
    val rate: String,
    val timestamp: String
){
    override fun toString(): String {
        return "Rate(ask='$ask', bid='$bid', close='$close', currency='$currency', high='$high', low='$low', `open`='$`open`', rate='$rate', timestamp='$timestamp')"
    }
}