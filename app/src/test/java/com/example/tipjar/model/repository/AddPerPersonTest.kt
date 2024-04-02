package com.example.tipjar.model.repository

import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AddPerPersonTest : PaymentRepositoryTest() {
    @Test
    fun `when addPerPerson is called, verify if addPerPerson of paymentSource is called`() {
        // Arrange
        paymentSource =
            mock {
                on {
                    addPerPerson(defaultPayment.countPerPerson)
                } doReturn defaultPayment.countPerPerson
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        repository.addPerPerson(defaultPayment.countPerPerson)

        // Assert
        verify(paymentSource).addPerPerson(defaultPayment.countPerPerson)
    }

    @Test
    fun `when addPerPerson is called, verify if we return the same string from paymentSource`() {
        // Arrange
        paymentSource =
            mock {
                on {
                    addPerPerson(defaultPayment.countPerPerson)
                } doReturn defaultPayment.countPerPerson
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.addPerPerson(defaultPayment.countPerPerson)

        // Assert
        val expectedResult = defaultPayment.countPerPerson
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when addPerPerson is called, verify if we return the same string from paymentSource (2)`() {
        // Arrange
        val otherSampleCountPerPerson = 10
        paymentSource =
            mock {
                on { addPerPerson(otherSampleCountPerPerson) } doReturn otherSampleCountPerPerson
            }
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.addPerPerson(otherSampleCountPerPerson)

        // Assert
        Assert.assertEquals(otherSampleCountPerPerson, result)
    }
}
