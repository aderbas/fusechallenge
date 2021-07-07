package com.aderbas.android.fusechallenge.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TwitterUtils {
    companion object {
        fun instance() : TwitterUtils {
            return TwitterUtils()
        }
    }

    fun dateFormat(stringDate: String): String {
        val format = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.US)
        val formatOut = SimpleDateFormat("mm/MM/yyyy", Locale.US)
        return  formatOut.format(format.parse(stringDate))
    }
}