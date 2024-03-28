package com.example.tipjar.model.entity

data class Computation(
    val hasError: Boolean = false,
    val totalTip: Float = 0F,
    val tipPerPerson: Int = 1,
)
