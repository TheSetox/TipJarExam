package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.Computation
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ComputePaymentTest : PaymentRepositoryTest() {
    @Test
    fun `when computePayment is called, verify if computePayment for paymentSource is called`() {
        // Arrange
        paymentSource =
            mock {
                on { computePayment(defaultPayment) } doReturn Computation()
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        repository.computePayment(defaultPayment)

        // Assert
        verify(paymentSource).computePayment(defaultPayment)
    }

    @Test
    fun `when computation is successful, return the expected result`() {
        // Arrange
        paymentSource =
            mock {
                on { computePayment(defaultPayment) } doReturn Computation()
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.computePayment(defaultPayment)

        // Assert
        val expectedResult = Computation()
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when there is an error in computing, return the expected result`() {
        // Arrange
        paymentSource =
            mock {
                on { computePayment(defaultPayment) } doReturn Computation(hasError = true)
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.computePayment(defaultPayment)

        // Assert
        val expectedResult = Computation(hasError = true)
        Assert.assertEquals(expectedResult, result)
    }
}
