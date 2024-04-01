package com.example.tipjar.model.source.impl

import com.example.tipjar.model.source.TimeStampSource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class TimeStampDataSource
    @Inject
    constructor() : TimeStampSource {
        override fun getTimeStamp(): String {
            return System.currentTimeMillis().toString()
        }

        override fun convertTimeStamp(timeStampMillis: String): String {
            val format = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            return format.format(Date(timeStampMillis.toLong()))
        }
    }
