package com.example.tipjar.model.source

import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.entity.ValidateResult

interface PaymentSource {
    fun validateAmount(amount: Float): ValidateResult

    fun validateCountPerPerson(countPerPerson: Int): ValidateResult

    fun validatePercentage(percentage: Float): ValidateResult

    fun computePayment(payment: Payment): Computation
}
