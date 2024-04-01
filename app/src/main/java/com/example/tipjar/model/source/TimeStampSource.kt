package com.example.tipjar.model.source

interface TimeStampSource {
    fun getTimeStamp(): String

    fun convertTimeStamp(timeStampMillis: String): String
}
