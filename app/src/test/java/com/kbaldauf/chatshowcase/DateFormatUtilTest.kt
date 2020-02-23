package com.kbaldauf.chatshowcase

import com.kbaldauf.chatshowcase.util.DateFormatUtil
import org.junit.Assert
import org.junit.Test
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month

class DateFormatUtilTest {

    @Test
    fun chatResponseHasProperChatType() {
        val date = LocalDateTime.of(2020, Month.FEBRUARY, 4, 10, 30)
        Assert.assertEquals(DateFormatUtil.instance.formatDateCommaTime(date), "Feb 04, 10:30 AM")
    }
}
