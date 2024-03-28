package com.example.tipjar.model.source.impl

import com.example.tipjar.model.source.TimeStampSource

class TimeStampDataSource : TimeStampSource {
    override fun getTimeStamp(): String {
        return System.currentTimeMillis().toString()
    }
}
