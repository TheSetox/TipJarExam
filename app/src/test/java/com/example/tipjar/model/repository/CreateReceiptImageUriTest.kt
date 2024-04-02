package com.example.tipjar.model.repository

import android.net.Uri
import com.example.tipjar.model.entity.ReceiptImage
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CreateReceiptImageUriTest : PaymentRepositoryTest() {
    @Test
    fun `when createReceiptImageUri is called, verify if getTimeStamp method is called`() {
        // Arrange
        val uri = mock<Uri>()
        timeStampSource = mock { on { getTimeStamp() } doReturn "" }
        imageSource = mock { on { createCaptureImageUri("") } doReturn uri }
        initializeRepository(currentTimeStampSource = timeStampSource)

        // Act
        repository.createReceiptImageUri()

        // Assert
        verify(timeStampSource).getTimeStamp()
    }

    @Test
    fun `when createReceiptImageUri is called, verify if createCaptureImageUri method is called`() {
        // Arrange
        val uri = mock<Uri>()
        timeStampSource = mock { on { getTimeStamp() } doReturn "" }
        imageSource = mock { on { createCaptureImageUri("") } doReturn uri }
        initializeRepository(currentTimeStampSource = timeStampSource)

        // Act
        repository.createReceiptImageUri()

        // Assert
        verify(imageSource).createCaptureImageUri("")
    }

    @Test
    fun `when createReceiptImageUri is called, verify if we get the needed data`() {
        // Arrange
        val uri = mock<Uri>()
        val timeStamp = "1"
        timeStampSource = mock { on { getTimeStamp() } doReturn timeStamp }
        imageSource = mock { on { createCaptureImageUri(timeStamp) } doReturn uri }
        initializeRepository(currentTimeStampSource = timeStampSource)

        // Act
        val result = repository.createReceiptImageUri()

        // Assert
        val expectedResult = ReceiptImage(uri, timeStamp)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when createReceiptImageUri is called, verify if we get the needed data (2)`() {
        // Arrange
        val uri = mock<Uri>()
        val timeStamp = "2"
        timeStampSource = mock { on { getTimeStamp() } doReturn timeStamp }
        imageSource = mock { on { createCaptureImageUri(timeStamp) } doReturn uri }
        initializeRepository(currentTimeStampSource = timeStampSource)

        // Act
        val result = repository.createReceiptImageUri()

        // Assert
        val expectedResult = ReceiptImage(uri, timeStamp)
        Assert.assertEquals(expectedResult, result)
    }
}
