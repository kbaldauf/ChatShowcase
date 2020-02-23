package com.kbaldauf.chatshowcase.manager

import com.kbaldauf.chatshowcase.R
import com.kbaldauf.chatshowcase.model.ChatMessage
import com.kbaldauf.chatshowcase.model.ChatMessage.ChatType

class ChatManager {

    // TODO: Replace with local db and/or web socket to yet to be built backend server
    fun generateServerMessage(): ChatMessage {
        return ChatMessage(ChatType.RESPONSE, "server message", R.drawable.ic_avatar_placeholder)
    }

    fun convertTextToMessage(message: String): ChatMessage {
        return ChatMessage(ChatType.MESSAGE, message)
    }

    companion object {
        val instance = ChatManager()
    }
}
