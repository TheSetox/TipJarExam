package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.PaymentHistory
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetListOfPaymentsTest : PaymentRepositoryTest() {
    @Test
    fun `when getListOfPayments is called, verify if localSource method is called`() {
        // Arrange
        localSource = mock { on { getListOfPayments() } doReturn emptyList() }
        initializeRepository(currentLocalSource = localSource)

        // Act
        repository.getListOfPayments()

        // Assert
        verify(localSource).getListOfPayments()
    }

    @Test
    fun `when getListOfPayments is empty, verify if the result is empty also`() {
        // Arrange
        localSource = mock { on { getListOfPayments() } doReturn emptyList() }
        initializeRepository(currentLocalSource = localSource)

        // Act
        val result = repository.getListOfPayments()

        // Assert
        val expectedResult = emptyList<PaymentHistory>()
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when getListOfPayments has one items, verify if the result is the same`() {
        // Arrange
        localSource = mock { on { getListOfPayments() } doReturn listOf(defaultPaymentHistory) }
        initializeRepository(currentLocalSource = localSource)

        // Act
        val result = repository.getListOfPayments()

        // Assert
        val expectedResult = listOf(defaultPaymentHistory)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when getListOfPayments has two and more items, verify if the result is the same`() {
        // Arrange
        val listOfPayments = listOf(defaultPaymentHistory, defaultPaymentHistory)
        localSource = mock { on { getListOfPayments() } doReturn listOfPayments }
        initializeRepository(currentLocalSource = localSource)

        // Act
        val result = repository.getListOfPayments()

        // Assert
        Assert.assertEquals(listOfPayments, result)
    }
}
