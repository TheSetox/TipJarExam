package com.example.tipjar.model.source.impl

import com.example.tipjar.model.database.TipDatabase
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.source.LocalSource
import javax.inject.Inject

class LocalDataSource
    @Inject
    constructor(db: TipDatabase) : LocalSource {
        private val paymentHistoryDao = db.paymentHistoryDao()

        override suspend fun savePayment(paymentHistory: PaymentHistory) {
            paymentHistoryDao.insert(paymentHistory)
        }

        override suspend fun getListOfPayments() = paymentHistoryDao.getListOfPaymentHistory()

        override suspend fun deletePayment(timeStamp: String) = paymentHistoryDao.delete(timeStamp)
    }
