package com.example.tipjar.model.repository

import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.io.File

class GetImageReceiptTest : PaymentRepositoryTest() {
    @Test
    fun `when getImageReceipt is called, verify if imageSource method is called`() {
        // Arrange
        imageSource =
            mock {
                on { getFileImage("") } doReturn File("defaultPath")
            }
        initializeRepository(currentImageSource = imageSource)

        // Act
        repository.getImageReceipt("")

        // Assert
        verify(imageSource).getFileImage("")
    }

    @Test
    fun `when getImageReceipt is called, verify if imageSource method is called (2)`() {
        // Arrange
        imageSource =
            mock {
                on { getFileImage("1") } doReturn File("defaultPath")
            }
        initializeRepository(currentImageSource = imageSource)

        // Act
        repository.getImageReceipt("1")

        // Assert
        verify(imageSource).getFileImage("1")
    }

    @Test
    fun `when getImageReceipt is called, verify if imageSource value is the same`() {
        // Arrange
        imageSource =
            mock {
                on { getFileImage("") } doReturn File("defaultPath")
            }
        initializeRepository(currentImageSource = imageSource)

        // Act
        val result = repository.getImageReceipt("")

        // Assert
        val expectedResult = File("defaultPath")
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when getImageReceipt is called, verify if imageSource value is the same (2)`() {
        // Arrange
        imageSource =
            mock {
                on { getFileImage("") } doReturn File("anotherPath")
            }
        initializeRepository(currentImageSource = imageSource)

        // Act
        val result = repository.getImageReceipt("")

        // Assert
        val expectedResult = File("anotherPath")
        Assert.assertEquals(expectedResult, result)
    }
}
