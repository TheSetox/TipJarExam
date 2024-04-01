package com.example.tipjar.model.source.impl

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.tipjar.model.source.ImageSource
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class ImageDataSource
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) : ImageSource {
        override fun createCaptureImageUri(timestamp: String): Uri {
            val photoFile = File(context.getExternalFilesDir(null), "$timestamp.jpg")
            return FileProvider.getUriForFile(
                context,
                "com.example.tipjar.fileprovider",
                photoFile,
            )
        }

        override fun getFileImage(id: String): File {
            return File(context.getExternalFilesDir(null), "$id.jpg")
        }
    }
