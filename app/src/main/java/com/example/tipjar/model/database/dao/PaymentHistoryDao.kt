package com.example.tipjar.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tipjar.model.entity.PaymentHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentHistoryDao {
    @Insert
    suspend fun insert(paymentHistory: PaymentHistory)

    @Query("SELECT * FROM payment_history ORDER BY timestamp DESC")
    fun getListOfPaymentHistory(): Flow<List<PaymentHistory>>

    @Query("DELETE FROM payment_history WHERE timestamp = :timeStamp")
    suspend fun delete(timeStamp: String)
}
