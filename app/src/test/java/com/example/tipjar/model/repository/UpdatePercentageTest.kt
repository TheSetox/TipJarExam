package com.example.tipjar.model.repository

import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class UpdatePercentageTest : PaymentRepositoryTest() {
    @Test
    fun `when updatePercentage is called, verify if updatePercentage of paymentSource is called`() {
        // Arrange
        paymentSource =
            mock {
                on {
                    updatePercentage(defaultPayment.percentage)
                } doReturn defaultPayment.percentage
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        repository.updatePercentage(defaultPayment.percentage)

        // Assert
        verify(paymentSource).updatePercentage(defaultPayment.percentage)
    }

    @Test
    fun `when updatePercentage is called, verify if we return the same string from paymentSource`() {
        // Arrange
        paymentSource =
            mock {
                on {
                    updatePercentage(defaultPayment.percentage)
                } doReturn defaultPayment.percentage
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.updatePercentage(defaultPayment.percentage)

        // Assert
        val expectedResult = defaultPayment.percentage
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when updatePercentage is called, verify if we return the same string from paymentSource (2)`() {
        // Arrange
        val otherSamplePercentage = "200"
        paymentSource =
            mock {
                on {
                    updatePercentage(otherSamplePercentage)
                } doReturn otherSamplePercentage
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.updatePercentage(otherSamplePercentage)

        // Assert
        Assert.assertEquals(otherSamplePercentage, result)
    }
}
