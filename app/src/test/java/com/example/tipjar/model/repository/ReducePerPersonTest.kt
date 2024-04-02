package com.example.tipjar.model.repository

import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ReducePerPersonTest : PaymentRepositoryTest() {
    @Test
    fun `when reducePerPerson is called, verify if reducePerPerson of paymentSource is called`() {
        // Arrange
        paymentSource =
            mock {
                on {
                    reducePerPerson(defaultPayment.countPerPerson)
                } doReturn defaultPayment.countPerPerson
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        repository.reducePerPerson(defaultPayment.countPerPerson)

        // Assert
        verify(paymentSource).reducePerPerson(defaultPayment.countPerPerson)
    }

    @Test
    fun `when reducePerPerson is called, verify if we return the same string from paymentSource`() {
        // Arrange
        paymentSource =
            mock {
                on {
                    reducePerPerson(defaultPayment.countPerPerson)
                } doReturn defaultPayment.countPerPerson
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.reducePerPerson(defaultPayment.countPerPerson)

        // Assert
        val expectedResult = defaultPayment.countPerPerson
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when reducePerPerson is called, verify if we return the same string from paymentSource (2)`() {
        // Arrange
        val otherSampleCountPerPerson = 10
        paymentSource =
            mock {
                on { reducePerPerson(otherSampleCountPerPerson) } doReturn otherSampleCountPerPerson
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.reducePerPerson(otherSampleCountPerPerson)

        // Assert
        Assert.assertEquals(otherSampleCountPerPerson, result)
    }
}
