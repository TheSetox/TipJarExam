package com.example.tipjar.model.source.impl

import android.util.Log
import androidx.core.text.isDigitsOnly
import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.source.PaymentSource
import javax.inject.Inject

class PaymentDataSource
    @Inject
    constructor() : PaymentSource {
        override fun updateAmount(amount: String): String {
            return addLimit(amount)
        }

        override fun updatePercentage(percentage: String): String {
            return addLimit(percentage)
        }

        override fun addPerPerson(countPerPerson: Int): Int {
            return countPerPerson + 1
        }

        override fun reducePerPerson(countPerPerson: Int): Int {
            return if (countPerPerson != 1) countPerPerson - 1 else countPerPerson
        }

        private fun addLimit(number: String): String {
            if (number.length <= 11 && number.isDigitsOnly()) {
                return number
            }
            return number.take(11)
        }

        override fun computePayment(payment: Payment): Computation {
            if (payment.amount.isNotBlank() && payment.percentage.isNotBlank() && payment.countPerPerson != 0) {
                try {
                    val amount = payment.amount.toFloat()
                    val percentage = payment.percentage.toFloat()
                    val totalTip = amount * (percentage / 100)
                    val tipPerPerson = totalTip / payment.countPerPerson
                    return Computation(totalTip = totalTip, tipPerPerson = tipPerPerson)
                } catch (e: Exception) {
                    Log.e("PaymentDataSource", "computePayment: ${e.message}")
                    Computation(hasError = true)
                }
            }
            return Computation()
        }
    }
