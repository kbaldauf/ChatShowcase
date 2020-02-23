package com.kbaldauf.chatshowcase.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kbaldauf.chatshowcase.R
import com.kbaldauf.chatshowcase.adapter.ChatAdapter
import com.kbaldauf.chatshowcase.model.ChatMessage
import com.kbaldauf.chatshowcase.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.view_chat_toolbar.*
import timber.log.Timber


class ChatActivity : AppCompatActivity(), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    private lateinit var gestureDetector: GestureDetector
    private lateinit var viewModel: ChatViewModel
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        gestureDetector = GestureDetector(this, this)
        setupChatList()
        addTextChangeListener()
        addOnClickListeners()

        viewModel.getMessages().observe(this, Observer<ChatMessage> {
            adapter.addMessage(it)
        })
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        viewModel.generateServerMessage()
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        if (sendButton.isActivated) {
            viewModel.sendMessage(inputField.text.toString())
            inputField.text.clear()
        }
        return true
    }

    // region private methods

    private fun setupChatList() {
        chatList.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        // new messages are added to bottom of the RecyclerView
        linearLayoutManager.stackFromEnd = true
        chatList.layoutManager = linearLayoutManager
        adapter = ChatAdapter()
        chatList.adapter = adapter

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                // auto scroll to ensure newly added message
                // is visible at the bottom of the screen
                chatList.smoothScrollToPosition(positionStart)
            }
        })
    }

    private fun addTextChangeListener() {
        inputField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                sendButton.isActivated = s?.length?.equals(0)?.not() ?: false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun addOnClickListeners() {
        sendButton.setOnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
        cameraButton.setOnClickListener {
            // TODO: Implement camera launcher
            Timber.i("camera button clicked")
        }
        galleryButton.setOnClickListener {
            //TODO: Implement gallery viewer
            Timber.i("gallery button clicked")
        }
        toolbar_call_button.setOnClickListener {
            //TODO: Implement call intent
            Timber.i("call button clicked")
        }
        toolbar_video_button.setOnClickListener {
            //TODO: Implement video call logic
            Timber.i("video button clicked")
        }
        toolbar_back.setOnClickListener {
            onBackPressed()
        }
    }

    // endregion

    // region no-op listener methods

    override fun onShowPress(e: MotionEvent?) {}

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {}

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return true
    }

    // endregion
}
