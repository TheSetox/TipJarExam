package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.PaymentHistory
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.random.Random

class SavePaymentTest : PaymentRepositoryTest() {
    private val timeStamp = randomTimeStamp().toString()

    private val paymentHistoryWithTimeStamp =
        PaymentHistory(
            timestamp = timeStamp,
            amount = defaultPayment.amount.toFloat(),
            tip = defaultPayment.totalTip,
            image = "",
        )

    private fun randomTimeStamp(): Long {
        val startTimestamp = 0L // January 1, 1970, 00:00:00 UTC
        val endTimestamp = System.currentTimeMillis() // Current timestamp

        return Random.nextLong(startTimestamp, endTimestamp)
    }

    @Test
    fun `when saving payment, verify if getTimeStamp is called`() =
        runTest {
            // Arrange
            timeStampSource = mock { on { getTimeStamp() } doReturn "" }
            initializeRepository(currentTimeStampSource = timeStampSource)

            // Act
            repository.savePayment(defaultPayment)

            // Assert
            verify(timeStampSource).getTimeStamp()
        }

    @Test
    fun `when saving payment with empty timestamp, verify if savePayment is called`() =
        runTest {
            // Arrange
            timeStampSource = mock { on { getTimeStamp() } doReturn "" }
            initializeRepository(
                currentTimeStampSource = timeStampSource,
                currentLocalSource = localSource,
            )

            // Act
            repository.savePayment(defaultPayment)

            // Assert
            verify(localSource).savePayment(defaultPaymentHistory)
        }

    @Test
    fun `when saving payment with timestamp, verify if savePayment with timeStamp is called`() =
        runTest {
            // Arrange
            timeStampSource = mock { on { getTimeStamp() } doReturn timeStamp }
            initializeRepository(
                currentTimeStampSource = timeStampSource,
                currentLocalSource = localSource,
            )

            // Act
            repository.savePayment(defaultPayment)

            // Assert
            verify(localSource).savePayment(paymentHistoryWithTimeStamp)
        }
}
