package com.example.tipjar.util

import java.text.NumberFormat
import java.util.Locale

fun Float.floatToCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(this.toDouble())
}