package com.example.tipjar.model.source

import com.example.tipjar.model.entity.PaymentHistory

interface LocalSource {
    suspend fun savePayment(paymentHistory: PaymentHistory)

    suspend fun getListOfPayments(): List<PaymentHistory>
}
