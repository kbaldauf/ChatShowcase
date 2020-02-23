package com.kbaldauf.chatshowcase.util

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class DateFormatUtil {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, h:mm a")

    fun formatDateCommaTime(date: LocalDateTime): String {
        return date.format(dateTimeFormatter)
    }

    companion object {
        val instance = DateFormatUtil()
    }
}
