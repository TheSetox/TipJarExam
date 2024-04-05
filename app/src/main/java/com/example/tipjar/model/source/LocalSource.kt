package com.example.tipjar.model.source

import com.example.tipjar.model.entity.PaymentHistory
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun savePayment(paymentHistory: PaymentHistory)

    suspend fun getListOfPayments(): Flow<List<PaymentHistory>>

    suspend fun deletePayment(timeStamp: String)
}
