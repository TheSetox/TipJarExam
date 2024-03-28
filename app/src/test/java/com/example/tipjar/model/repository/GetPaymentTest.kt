package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.PaymentHistory
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetPaymentTest : PaymentRepositoryTest() {
    @Test
    fun `when getPayment is called, verify if localSource method is called`() {
        // Arrange
        localSource = mock { on { getPayment("") } doReturn defaultPaymentHistory }
        initializeRepository(currentLocalSource = localSource)

        // Act
        repository.getPayment("")

        // Assert
        verify(localSource).getPayment("")
    }

    @Test
    fun `when getPayment is called, verify if localSource method with the same parameter`() {
        // Arrange
        localSource = mock { on { getPayment("1") } doReturn defaultPaymentHistory }
        initializeRepository(currentLocalSource = localSource)

        // Act
        repository.getPayment("1")

        // Assert
        verify(localSource).getPayment("1")
    }

    @Test
    fun `when getPayment is called, verify if localSource value is the same`() {
        // Arrange
        localSource = mock { on { getPayment("") } doReturn defaultPaymentHistory }
        initializeRepository(currentLocalSource = localSource)

        // Act
        val result = repository.getPayment("")

        // Assert
        val expectedResult = defaultPaymentHistory
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when getPayment is called, verify if localSource value is the same (2)`() {
        // Arrange
        val customPaymentHistory =
            PaymentHistory(
                timestamp = "1",
                amount = 200F,
                tip = 20F,
                image = "",
            )
        localSource = mock { on { getPayment("") } doReturn customPaymentHistory }
        initializeRepository(currentLocalSource = localSource)

        // Act
        val result = repository.getPayment("")

        // Assert
        Assert.assertEquals(customPaymentHistory, result)
    }
}
