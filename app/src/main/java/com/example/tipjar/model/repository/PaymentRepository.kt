package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.entity.ReceiptImage
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PaymentRepository {
    fun updateAmount(amount: String): String

    fun updatePercentage(percentage: String): String

    fun addPerPerson(countPerPerson: Int): Int

    fun reducePerPerson(countPerPerson: Int): Int

    fun computePayment(payment: Payment): Computation

    fun createReceiptImageUri(): ReceiptImage

    suspend fun savePayment(payment: Payment)

    suspend fun getListOfPayments(): Flow<List<PaymentHistory>>

    fun getImageReceipt(id: String): File

    suspend fun deletePayment(timeStamp: String)
}
