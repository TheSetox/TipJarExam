package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.Payment
import com.example.tipjar.model.entity.PaymentHistory
import com.example.tipjar.model.source.ImageSource
import com.example.tipjar.model.source.LocalSource
import com.example.tipjar.model.source.PaymentSource
import com.example.tipjar.model.source.TimeStampSource
import org.mockito.kotlin.mock

open class PaymentRepositoryTest {
    internal var repository: PaymentRepository = mock()
    internal var paymentSource: PaymentSource = mock()
    internal var localSource: LocalSource = mock()
    internal var timeStampSource: TimeStampSource = mock()
    internal var imageSource: ImageSource = mock()

    internal val defaultPayment = Payment()

    internal val defaultPaymentHistory =
        PaymentHistory(
            timestamp = "",
            amount = defaultPayment.amount.toFloat(),
            tip = defaultPayment.totalTip,
            image = "",
        )

    internal fun initializeRepository(
        currentPaymentSource: PaymentSource = paymentSource,
        currentLocalSource: LocalSource = localSource,
        currentTimeStampSource: TimeStampSource = timeStampSource,
        currentImageSource: ImageSource = imageSource,
    ) {
        repository =
            PaymentDataRepository(
                currentImageSource,
                currentLocalSource,
                currentPaymentSource,
                currentTimeStampSource,
            )
    }
}
