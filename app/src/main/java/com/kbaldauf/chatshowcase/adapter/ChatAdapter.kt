package com.kbaldauf.chatshowcase.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kbaldauf.chatshowcase.R
import com.kbaldauf.chatshowcase.model.ChatMessage
import com.kbaldauf.chatshowcase.model.ChatMessage.ChatType


class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private val messages: MutableList<ChatMessage> = ArrayList()

    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyItemInserted(messages.size)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        return messages[position].messageType.ordinal
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        val type = ChatType.values()[viewType]
        val layout: Int =
            if (type === ChatType.MESSAGE) R.layout.view_chat_message else R.layout.view_chat_response
        val inflatedView: View =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ChatViewHolder(inflatedView)
    }

    override fun onBindViewHolder(
        holder: ChatViewHolder,
        position: Int
    ) {
        holder.bind(messages[position])
    }

    inner class ChatViewHolder(itemView: View) : ViewHolder(itemView), View.OnLongClickListener,
        View.OnClickListener {
        private val message: TextView = itemView.findViewById(R.id.message)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val profilePicture: ImageView = itemView.findViewById(R.id.profile_picture)

        init {
            message.setOnLongClickListener(this)
            message.setOnClickListener(this)
        }

        fun bind(message: ChatMessage) {
            // set text
            this.message.text = message.message
            this.message.movementMethod = LinkMovementMethod.getInstance()
            Linkify.addLinks(this.message, Linkify.ALL)

            // set date
            this.date.visibility = View.INVISIBLE
            this.date.text = message.getDateString()

            // set profile picture if present otherwise clear prior image
            message.profilePicture?.let {
                this.profilePicture.setImageResource(it)
            } ?: run {
                this.profilePicture.setImageDrawable(null)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            val clipboard =
                itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("chat message", message.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(itemView.context, R.string.text_copied_message, Toast.LENGTH_SHORT)
                .show()
            return true
        }

        override fun onClick(v: View?) {
            val isVisible = date.visibility
            date.visibility = if (isVisible == View.VISIBLE) {
                View.INVISIBLE
            } else {
                View.VISIBLE
            }
        }
    }
}
