package com.example.tipjar.model.repository

import android.content.Intent
import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.entity.PaymentHistory
import java.io.File

interface PaymentRepository {
    fun updateAmount(amount: String): String

    fun updatePercentage(percentage: String): String

    fun addPerPerson(countPerPerson: Int): Int

    fun reducePerPerson(countPerPerson: Int): Int

    fun computePayment(payment: Payment): Computation

    fun createReceiptImageIntent(): Intent

    suspend fun savePayment(payment: Payment)

    suspend fun getListOfPayments(): List<PaymentHistory>

    fun getImageReceipt(id: String): File
}
