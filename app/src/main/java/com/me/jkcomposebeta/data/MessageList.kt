package com.me.jkcompose.data

import androidx.compose.runtime.MutableState

data class MessageItem(
    val messageId: Long,
    val user: User,
    val content: String,
    val isLike: MutableState<Boolean>,
    val time:String,
    var likeNum :MutableState<Int>
    )

data class MessageList(
    val messageListId: Long,
    val messages: List<MessageItem>
)