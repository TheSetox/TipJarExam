package com.example.tipjar.model.source.impl

import android.content.Intent
import com.example.tipjar.model.source.ImageSource
import java.io.File
import javax.inject.Inject

class ImageDataSource
    @Inject
    constructor() : ImageSource {
        override fun createCaptureImageIntent(timestamp: String): Intent {
            TODO("Not yet implemented")
        }

        override fun getFileImage(id: String): File {
            TODO("Not yet implemented")
        }
    }
