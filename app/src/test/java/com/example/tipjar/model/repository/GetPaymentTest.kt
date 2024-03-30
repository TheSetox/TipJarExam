package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.PaymentHistory
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetPaymentTest : PaymentRepositoryTest() {
    @Test
    fun `when getPayment is called, verify if localSource method is called`() =
        runTest {
            // Arrange
            localSource = mock { onBlocking { getPayment("") } doReturn defaultPaymentHistory }
            initializeRepository(currentLocalSource = localSource)

            // Act
            repository.getPayment("")

            // Assert
            verify(localSource).getPayment("")
        }

    @Test
    fun `when getPayment is called, verify if localSource method with the same parameter`() =
        runTest {
            // Arrange
            localSource = mock { onBlocking { getPayment("1") } doReturn defaultPaymentHistory }
            initializeRepository(currentLocalSource = localSource)

            // Act
            repository.getPayment("1")

            // Assert
            verify(localSource).getPayment("1")
        }

    @Test
    fun `when getPayment is called, verify if localSource value is the same`() =
        runTest {
            // Arrange
            localSource = mock { onBlocking { getPayment("") } doReturn defaultPaymentHistory }
            initializeRepository(currentLocalSource = localSource)

            // Act
            val result = repository.getPayment("")

            // Assert
            val expectedResult = defaultPaymentHistory
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when getPayment is called, verify if localSource value is the same (2)`() =
        runTest {
            // Arrange
            val customPaymentHistory =
                PaymentHistory(
                    timestamp = "1",
                    amount = 200F,
                    tip = 20F,
                    image = "",
                )
            localSource = mock { onBlocking { getPayment("") } doReturn customPaymentHistory }
            initializeRepository(currentLocalSource = localSource)

            // Act
            val result = repository.getPayment("")

            // Assert
            Assert.assertEquals(customPaymentHistory, result)
        }
}
