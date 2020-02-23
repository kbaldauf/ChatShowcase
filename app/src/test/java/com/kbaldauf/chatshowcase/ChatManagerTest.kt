package com.kbaldauf.chatshowcase

import com.kbaldauf.chatshowcase.manager.ChatManager
import com.kbaldauf.chatshowcase.model.ChatMessage
import org.junit.Assert
import org.junit.Test

class ChatManagerTest {

    @Test
    fun chatResponseHasProperChatType() {
        val response = ChatManager.instance.generateServerMessage()
        Assert.assertEquals(response.messageType, ChatMessage.ChatType.RESPONSE)
    }

    @Test
    fun chatMessageHasProperChatType() {
        val message = ChatManager.instance.convertTextToMessage("test")
        Assert.assertEquals(message.messageType, ChatMessage.ChatType.MESSAGE)
    }

    @Test
    fun chatResponseHasProfileImage() {
        val response = ChatManager.instance.generateServerMessage()
        Assert.assertNotNull(response.profilePicture)
    }

    @Test
    fun chatMessageHasNoProfileImage() {
        val message = ChatManager.instance.convertTextToMessage("test")
        Assert.assertNull(message.profilePicture)
    }
}
