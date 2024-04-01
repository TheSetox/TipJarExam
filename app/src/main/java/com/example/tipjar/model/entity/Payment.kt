package com.example.tipjar.model.entity

data class Payment(
    var amount: String = "100",
    var percentage: String = "10",
    var countPerPerson: Int = 1,
    val totalTip: Float = 0F,
    val totalTipPerPerson: Float = 0F,
    val imageFile: String = "",
)
