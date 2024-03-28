package com.example.tipjar.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "payment_history", primaryKeys = ["timestamp"])
data class PaymentHistory(
    @ColumnInfo(name = "timestamp") val timestamp: String,
    @ColumnInfo(name = "amount") val amount: Float,
    @ColumnInfo(name = "tip") val tip: Float,
    @ColumnInfo(name = "image") val image: String,
)
