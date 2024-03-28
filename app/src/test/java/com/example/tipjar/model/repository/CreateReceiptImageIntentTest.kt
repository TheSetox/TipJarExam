package com.example.tipjar.model.repository

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CreateReceiptImageIntentTest : PaymentRepositoryTest() {
    @Test
    fun `when create receipt image intent is called, verify if timestamp is called`() {
        // Arrange
        timeStampSource = mock { on { getTimeStamp() } doReturn "" }
        initializeRepository(currentTimeStampSource = timeStampSource)

        // Act
        repository.createReceiptImageIntent()

        // Assert
        verify(timeStampSource).getTimeStamp()
    }

    @Test
    fun `when createReceiptImageIntent is called, verify if imageSource method is called`() {
        // Arrange
        timeStampSource = mock { on { getTimeStamp() } doReturn "" }
        imageSource = mock { on { createCaptureImageIntent("") } doReturn Intent() }
        initializeRepository(currentImageSource = imageSource)

        // Act
        repository.createReceiptImageIntent()

        // Assert
        verify(imageSource).createCaptureImageIntent("")
    }

    @Test
    fun `when createReceiptImageIntent is called, verify if the action name is expected`() {
        // Arrange
        val intentWithAction: Intent =
            mock<Intent> {
                on { action } doReturn MediaStore.ACTION_IMAGE_CAPTURE
            }
        timeStampSource = mock { on { getTimeStamp() } doReturn "" }
        imageSource =
            mock {
                on { createCaptureImageIntent("") } doReturn intentWithAction
            }
        initializeRepository(currentImageSource = imageSource)

        // Act
        val intentResult = repository.createReceiptImageIntent()
        val result = intentResult.action

        // Assert
        Assert.assertEquals(MediaStore.ACTION_IMAGE_CAPTURE, result)
    }

    @Test
    fun `when action name is empty when fetching in imageSource, return empty intent`() {
        // Arrange
        val intent: Intent = mock<Intent>()
        timeStampSource = mock { on { getTimeStamp() } doReturn "" }
        imageSource =
            mock {
                on { createCaptureImageIntent("") } doReturn intent
            }
        initializeRepository(currentImageSource = imageSource)

        // Act
        val result = repository.createReceiptImageIntent()

        // Assert
        Assert.assertEquals(intent, result)
    }

    @Test
    fun `when createReceiptImageIntent is called, verify if the uri can be called`() {
        // Arrange
        val uri = mock<Uri>()
        val intent: Intent =
            mock<Intent> {
                on { getParcelableExtra(MediaStore.EXTRA_OUTPUT, Uri::class.java) } doReturn uri
            }
        timeStampSource = mock { on { getTimeStamp() } doReturn "" }
        imageSource =
            mock {
                on { createCaptureImageIntent("") } doReturn intent
            }
        initializeRepository(currentImageSource = imageSource)

        // Act
        val intentResult = repository.createReceiptImageIntent()
        val result = intentResult.getParcelableExtra(MediaStore.EXTRA_OUTPUT, Uri::class.java)

        // Assert
        Assert.assertEquals(uri, result)
    }
}
