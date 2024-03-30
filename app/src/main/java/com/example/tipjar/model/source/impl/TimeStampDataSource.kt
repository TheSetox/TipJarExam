package com.example.tipjar.model.source.impl

import com.example.tipjar.model.source.TimeStampSource
import javax.inject.Inject

class TimeStampDataSource
    @Inject
    constructor() : TimeStampSource {
        override fun getTimeStamp(): String {
            return System.currentTimeMillis().toString()
        }
    }
