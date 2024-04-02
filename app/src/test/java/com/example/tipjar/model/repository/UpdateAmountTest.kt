package com.example.tipjar.model.repository

import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class UpdateAmountTest : PaymentRepositoryTest() {
    @Test
    fun `when updateAmount is called, verify if updateAmount of paymentSource is called`() {
        // Arrange
        paymentSource =
            mock {
                on { updateAmount(defaultPayment.amount) } doReturn defaultPayment.amount
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        repository.updateAmount(defaultPayment.amount)

        // Assert
        verify(paymentSource).updateAmount(defaultPayment.amount)
    }

    @Test
    fun `when updateAmount is called, verify if we return the same string from paymentSource`() {
        // Arrange
        paymentSource =
            mock {
                on { updateAmount(defaultPayment.amount) } doReturn defaultPayment.amount
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.updateAmount(defaultPayment.amount)

        // Assert
        val expectedResult = defaultPayment.amount
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when updateAmount is called, verify if we return the same string from paymentSource (2)`() {
        // Arrange
        val otherSampleAmount = "200"
        paymentSource =
            mock {
                on { updateAmount(otherSampleAmount) } doReturn otherSampleAmount
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.updateAmount(otherSampleAmount)

        // Assert
        Assert.assertEquals(otherSampleAmount, result)
    }
}
