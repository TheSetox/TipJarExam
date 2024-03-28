package com.example.tipjar.model.source

import com.example.tipjar.model.entity.PaymentHistory

interface LocalSource {
    fun savePayment(paymentHistory: PaymentHistory)

    fun getListOfPayments(): List<PaymentHistory>

    fun getPayment(id: String): PaymentHistory
}
