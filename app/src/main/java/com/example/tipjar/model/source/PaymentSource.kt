package com.example.tipjar.model.source

import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment

interface PaymentSource {
    fun updateAmount(amount: String): String

    fun updatePercentage(percentage: String): String

    fun addPerPerson(countPerPerson: Int): Int

    fun reducePerPerson(countPerPerson: Int): Int

    fun computePayment(payment: Payment): Computation
}
