package com.example.tipjar.model.repository

import android.content.Intent
import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.source.ImageSource
import com.example.tipjar.model.source.LocalSource
import com.example.tipjar.model.source.PaymentSource
import com.example.tipjar.model.source.TimeStampSource
import java.io.File
import javax.inject.Inject

class PaymentDataRepository
    @Inject
    constructor(
        private val imageSource: ImageSource,
        private val localSource: LocalSource,
        private val paymentSource: PaymentSource,
        private val timeStampSource: TimeStampSource,
    ) : PaymentRepository {
        override fun updateAmount(amount: String): String {
            return paymentSource.updateAmount(amount)
        }

        override fun updatePercentage(percentage: String): String {
            return paymentSource.updatePercentage(percentage)
        }

        override fun addPerPerson(countPerPerson: Int): Int {
            return paymentSource.addPerPerson(countPerPerson)
        }

        override fun reducePerPerson(countPerPerson: Int): Int {
            return paymentSource.reducePerPerson(countPerPerson)
        }

        override fun computePayment(payment: Payment): Computation {
            return paymentSource.computePayment(payment)
        }

        override fun createReceiptImageIntent(): Intent {
            val timeStamp = timeStampSource.getTimeStamp()
            return imageSource.createCaptureImageIntent(timeStamp)
        }

        override suspend fun savePayment(payment: Payment) {
            val timeStamp = timeStampSource.getTimeStamp()
            val paymentHistory =
                PaymentHistory(
                    timestamp = timeStamp,
                    amount = payment.amount.toFloat(),
                    tip = payment.totalTip,
                    image = payment.imageFile,
                )
            localSource.savePayment(paymentHistory)
        }

        override suspend fun getListOfPayments(): List<PaymentHistory> {
            return localSource.getListOfPayments().map {
                it.copy(timestamp = timeStampSource.convertTimeStamp(it.timestamp))
            }
        }

        override fun getImageReceipt(id: String): File {
            return imageSource.getFileImage(id)
        }
    }
