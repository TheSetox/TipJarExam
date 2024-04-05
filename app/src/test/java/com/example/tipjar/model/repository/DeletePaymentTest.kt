package com.example.tipjar.model.repository

import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.verify
import kotlin.random.Random

class DeletePaymentTest : PaymentRepositoryTest() {
    private val timeStamp = randomTimeStamp().toString()

    private fun randomTimeStamp(): Long {
        val startTimestamp = 0L // January 1, 1970, 00:00:00 UTC
        val endTimestamp = System.currentTimeMillis() // Current timestamp

        return Random.nextLong(startTimestamp, endTimestamp)
    }

    @Test
    fun `when deleting payment, verify if localSource deletePayment is called`() =
        runTest {
            // Arrange
            initializeRepository()

            // Act
            repository.deletePayment("")

            // Assert
            verify(localSource).deletePayment("")
        }

    @Test
    fun `when deleting payment with timestamp, verify if deletePayment with timeStamp is called`() =
        runTest {
            // Arrange
            initializeRepository()

            // Act
            repository.deletePayment(timeStamp)

            // Assert
            verify(localSource).deletePayment(timeStamp)
        }
}
