package com.example.tipjar.model.source.impl

import com.example.tipjar.model.entity.Computation
import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.entity.ValidateResult
import com.example.tipjar.model.source.PaymentSource
import javax.inject.Inject

class PaymentDataSource
    @Inject
    constructor() : PaymentSource {
        override fun validateAmount(amount: Float): ValidateResult {
            TODO("Not yet implemented")
        }

        override fun validateCountPerPerson(countPerPerson: Int): ValidateResult {
            TODO("Not yet implemented")
        }

        override fun validatePercentage(percentage: Float): ValidateResult {
            TODO("Not yet implemented")
        }

        override fun computePayment(payment: Payment): Computation {
            TODO("Not yet implemented")
        }
    }
