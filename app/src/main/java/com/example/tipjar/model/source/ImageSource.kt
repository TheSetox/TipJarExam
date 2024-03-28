package com.example.tipjar.model.source

import android.content.Intent
import java.io.File

interface ImageSource {
    fun createCaptureImageIntent(timestamp: String): Intent

    fun getFileImage(id: String): File
}
