package com.example.tipjar.model.repository

import android.content.Intent
import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.entity.Validation
import java.io.File

interface PaymentRepository {
    fun validatePayment(payment: Payment): Validation

    fun computePayment(payment: Payment): Computation

    fun createReceiptImageIntent(): Intent

    suspend fun savePayment(payment: Payment)

    suspend fun getListOfPayments(): List<PaymentHistory>

    suspend fun getPayment(id: String): PaymentHistory

    fun getImageReceipt(id: String): File
}
