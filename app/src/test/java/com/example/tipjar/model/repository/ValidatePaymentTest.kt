package com.example.tipjar.model.repository

import com.example.tipjar.model.entity.ValidateResult
import com.example.tipjar.model.entity.Validation
import com.example.tipjar.model.source.PaymentSource
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ValidatePaymentTest : PaymentRepositoryTest() {
    private val successResult = ValidateResult(isVerified = true)
    private val defaultResult = ValidateResult()

    private fun preparePaymentSource(
        validateAmountResult: ValidateResult,
        validateTipPercentage: ValidateResult,
        validateCountPerPerson: ValidateResult,
    ) {
        paymentSource =
            mock<PaymentSource> {
                on { validateAmount(defaultPayment.amount) } doReturn validateAmountResult
                on { validatePercentage(defaultPayment.percentage) } doReturn validateTipPercentage
                on {
                    validateCountPerPerson(defaultPayment.countPerPerson)
                } doReturn validateCountPerPerson
            }
    }

    private fun defaultPreparation() {
        preparePaymentSource(defaultResult, defaultResult, defaultResult)
        initializeRepository(currentPaymentSource = paymentSource)
    }

    @Test
    fun `when validating the payment, verify if we called validateAmount`() {
        // Arrange
        defaultPreparation()

        // Act
        repository.validatePayment(defaultPayment)

        // Assert
        verify(paymentSource).validateAmount(defaultPayment.amount)
    }

    @Test
    fun `when validating the payment, verify if we called validatePercentage`() {
        // Arrange
        defaultPreparation()

        // Act
        repository.validatePayment(defaultPayment)

        // Assert
        verify(paymentSource).validatePercentage(defaultPayment.percentage)
    }

    @Test
    fun `when validating the payment, verify if we called validateCountPerPerson`() {
        // Arrange
        defaultPreparation()

        // Act
        repository.validatePayment(defaultPayment)

        // Assert
        verify(paymentSource).validateCountPerPerson(defaultPayment.countPerPerson)
    }

    @Test
    fun `when validation is successful, verify if it is the expected result`() {
        // Arrange
        preparePaymentSource(successResult, successResult, successResult)
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.validatePayment(defaultPayment)

        // Assert
        val expectedResult = Validation(successResult, successResult, successResult)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when validation is not successful, verify if it is the expected result`() {
        // Arrange
        preparePaymentSource(defaultResult, successResult, successResult)
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.validatePayment(defaultPayment)

        // Assert
        val expectedResult = Validation(defaultResult, successResult, successResult)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when validation is not successful, verify if it is the expected result (2)`() {
        // Arrange
        preparePaymentSource(successResult, successResult, defaultResult)
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.validatePayment(defaultPayment)

        // Assert
        val expectedResult = Validation(successResult, defaultResult, successResult)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when validation is not successful, verify if it is the expected result (3)`() {
        // Arrange
        preparePaymentSource(successResult, defaultResult, successResult)
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.validatePayment(defaultPayment)

        // Assert
        val expectedResult = Validation(successResult, successResult, defaultResult)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when validation is not successful, verify if it is the expected result (4)`() {
        // Arrange
        preparePaymentSource(defaultResult, successResult, defaultResult)
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.validatePayment(defaultPayment)

        // Assert
        val expectedResult = Validation(defaultResult, defaultResult, successResult)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when validation is not successful, verify if it is the expected result (5)`() {
        // Arrange
        preparePaymentSource(successResult, defaultResult, defaultResult)
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.validatePayment(defaultPayment)

        // Assert
        val expectedResult = Validation(successResult, defaultResult, defaultResult)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when validation is not successful, verify if it is the expected result (6)`() {
        // Arrange
        preparePaymentSource(defaultResult, defaultResult, successResult)
        initializeRepository(currentPaymentSource = paymentSource)

        // Act
        val result = repository.validatePayment(defaultPayment)

        // Assert
        val expectedResult = Validation(defaultResult, successResult, defaultResult)
        Assert.assertEquals(expectedResult, result)
    }
}
