package com.kbaldauf.chatshowcase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kbaldauf.chatshowcase.manager.ChatManager
import com.kbaldauf.chatshowcase.model.ChatMessage

class ChatViewModel : ViewModel() {
    private val chatLiveData = MutableLiveData<ChatMessage>()

    fun sendMessage(message: String) {
        chatLiveData.postValue(ChatManager.instance.convertTextToMessage(message))
    }

    fun generateServerMessage() {
        chatLiveData.postValue(ChatManager.instance.generateServerMessage())
    }

    fun getMessages(): LiveData<ChatMessage> {
        return chatLiveData
    }
}
