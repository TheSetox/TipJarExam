package com.example.tipjar.model.entity

data class Payment(
    val amount: Float = 100F,
    val percentage: Float = 10F,
    val countPerPerson: Int = 1,
    val totalTip: Float = 0F,
    val totalTipPerPerson: Float = 0F,
    val imageFile: String = "",
)
