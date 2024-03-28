package com.example.tipjar.model.repository

import android.content.Intent
import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.entity.Validation
import com.example.tipjar.model.source.ImageSource
import com.example.tipjar.model.source.LocalSource
import com.example.tipjar.model.source.PaymentSource
import com.example.tipjar.model.source.TimeStampSource
import java.io.File

class PaymentDataRepository(
    private val imageSource: ImageSource,
    private val localSource: LocalSource,
    private val paymentSource: PaymentSource,
    private val timeStampSource: TimeStampSource,
) : PaymentRepository {
    override fun validatePayment(payment: Payment): Validation {
        val validateAmount = paymentSource.validateAmount(payment.amount)
        val validatePercentage = paymentSource.validatePercentage(payment.percentage)
        val validateCountPerPerson = paymentSource.validateCountPerPerson(payment.countPerPerson)
        return Validation(validateAmount, validateCountPerPerson, validatePercentage)
    }

    override fun computePayment(payment: Payment): Computation {
        return paymentSource.computePayment(payment)
    }

    override fun createReceiptImageIntent(): Intent {
        val timeStamp = timeStampSource.getTimeStamp()
        return imageSource.createCaptureImageIntent(timeStamp)
    }

    override fun savePayment(payment: Payment) {
        val timeStamp = timeStampSource.getTimeStamp()
        val paymentHistory =
            PaymentHistory(
                timestamp = timeStamp,
                amount = payment.amount,
                tip = payment.totalTip,
                image = payment.imageFile,
            )
        localSource.savePayment(paymentHistory)
    }

    override fun getListOfPayments(): List<PaymentHistory> {
        return localSource.getListOfPayments()
    }

    override fun getPayment(id: String): PaymentHistory {
        return localSource.getPayment(id)
    }

    override fun getImageReceipt(id: String): File {
        return imageSource.getFileImage(id)
    }
}
