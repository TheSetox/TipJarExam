package com.example.tipjar.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tipjar.model.entity.PaymentHistory

@Dao
interface PaymentHistoryDao {
    @Insert
    suspend fun insert(paymentHistory: PaymentHistory)

    @Query("SELECT * FROM payment_history")
    suspend fun getListOfPaymentHistory(): List<PaymentHistory>

    @Query("SELECT * FROM payment_history WHERE timestamp = :id")
    suspend fun getPaymentHistory(id: String): PaymentHistory
}
