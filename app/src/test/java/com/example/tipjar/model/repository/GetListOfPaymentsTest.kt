package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.PaymentHistory
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetListOfPaymentsTest : PaymentRepositoryTest() {
    @Test
    fun `when getListOfPayments is called, verify if localSource method is called`() =
        runTest {
            // Arrange
            localSource = mock { onBlocking { getListOfPayments() } doReturn emptyFlow() }
            initializeRepository(currentLocalSource = localSource)

            // Act
            repository.getListOfPayments()

            // Assert
            verify(localSource).getListOfPayments()
        }

    @Test
    fun `when getListOfPayments is empty, verify if the result is empty also`() =
        runTest {
            // Arrange
            localSource = mock { onBlocking { getListOfPayments() } doReturn emptyFlow() }
            initializeRepository(currentLocalSource = localSource)

            // Act
            val result = repository.getListOfPayments()

            // Assert
            val expectedResult = emptyFlow<List<PaymentHistory>>()
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when getListOfPayments has one items, verify if the result is the same`() =
        runTest {
            // Arrange
            val listOfPayment = flow<List<PaymentHistory>> { listOf(defaultPaymentHistory) }
            localSource = mock { onBlocking { getListOfPayments() } doReturn listOfPayment }
            initializeRepository(currentLocalSource = localSource)

            // Act
            val result = repository.getListOfPayments()

            // Assert
            Assert.assertEquals(listOfPayment, result)
        }

    @Test
    fun `when getListOfPayments has two and more items, verify if the result is the same`() =
        runTest {
            // Arrange
            val listOfPayments =
                flow<List<PaymentHistory>> {
                    listOf(
                        defaultPaymentHistory,
                        defaultPaymentHistory,
                    )
                }
            localSource = mock { onBlocking { getListOfPayments() } doReturn listOfPayments }
            initializeRepository(currentLocalSource = localSource)

            // Act
            val result = repository.getListOfPayments()

            // Assert
            Assert.assertEquals(listOfPayments, result)
        }
}
