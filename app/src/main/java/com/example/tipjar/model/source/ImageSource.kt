package com.example.tipjar.model.source

import android.net.Uri
import java.io.File

interface ImageSource {
    fun createCaptureImageUri(timestamp: String): Uri

    fun getFileImage(id: String): File
}
