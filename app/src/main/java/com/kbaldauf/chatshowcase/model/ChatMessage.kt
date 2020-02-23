package com.kbaldauf.chatshowcase.model

import androidx.annotation.DrawableRes
import com.kbaldauf.chatshowcase.util.DateFormatUtil
import org.threeten.bp.LocalDateTime

data class ChatMessage(
    val messageType: ChatType,
    val message: String,
    @DrawableRes val profilePicture: Int? = null
) {
    val timestamp: LocalDateTime = LocalDateTime.now()

    enum class ChatType {
        MESSAGE, RESPONSE
    }

    fun getDateString(): String {
        return DateFormatUtil.instance.formatDateCommaTime(timestamp)
    }

}
