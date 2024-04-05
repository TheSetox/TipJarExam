package com.example.tipjar.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Float.floatToCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(this.toDouble())
}

fun String.convertTimeStampToDate(): String {
    val format = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return format.format(Date(toLong()))
}
