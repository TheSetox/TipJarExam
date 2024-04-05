package com.example.tipjar.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Entity(tableName = "payment_history", primaryKeys = ["timestamp"])
data class PaymentHistory(
    @ColumnInfo(name = "timestamp") val timestamp: String,
    @ColumnInfo(name = "amount") val amount: Float,
    @ColumnInfo(name = "tip") val tip: Float,
    @ColumnInfo(name = "image") val image: String,
) {
    companion object {
        fun Companion.defaultData(): PaymentHistory {
            return PaymentHistory("", 0F, 0F, "")
        }

        private fun Companion.previewData(): PaymentHistory {
            return PaymentHistory("February 20, 2024", 100F, 10F, "")
        }

        private fun Companion.previewData2(): PaymentHistory {
            return PaymentHistory("February 21, 2024", 200F, 20F, "")
        }

        fun Companion.previewList(): Flow<List<PaymentHistory>> {
            return flow { listOf(previewData(), previewData2()) }
        }
    }
}
