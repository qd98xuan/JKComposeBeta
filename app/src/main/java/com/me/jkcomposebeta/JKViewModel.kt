package com.me.jkcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.me.jkcompose.data.MessageItem
import com.me.jkcompose.data.MessageList
import com.me.jkcompose.data.User
import com.me.jkcomposebeta.R

class JKViewModel : ViewModel() {
    var selectedTab by mutableStateOf(0)
    var messageData by mutableStateOf(
        MessageList(
            1,
            listOf(
                MessageItem(0, User(0, "会飞的鱼", R.drawable.boy_1), "这是第一天", mutableStateOf(false), "1小时前",mutableStateOf(0)),
                MessageItem(
                    1,
                    User(1, "会飞的鸡", R.drawable.girl_1),
                    "这是第二天",
                    mutableStateOf(false),
                    "2小时前", mutableStateOf(0)
                ),
                MessageItem(2, User(2, "会飞的鸟", R.drawable.girl_2), "这是第三天", mutableStateOf(false), "3小时前",mutableStateOf(0)),
                MessageItem(0, User(0, "会飞的鱼", R.drawable.boy_1), "这是第一天", mutableStateOf(false), "1小时前",mutableStateOf(0)),
                MessageItem(
                    1,
                    User(1, "会飞的鸡", R.drawable.girl_1),
                    "这是第二天",
                    mutableStateOf(false),
                    "2小时前",mutableStateOf(0)
                ),
                MessageItem(2, User(2, "会飞的鸟", R.drawable.girl_2), "这是第三天", mutableStateOf(false), "3小时前",mutableStateOf(0)),
                MessageItem(0, User(0, "会飞的鱼", R.drawable.boy_1), "这是第一天", mutableStateOf(false), "1小时前",mutableStateOf(0)),
                MessageItem(
                    1,
                    User(1, "会飞的鸡", R.drawable.girl_1),
                    "这是第二天",
                    mutableStateOf(false),
                    "2小时前",mutableStateOf(0)
                ),
                MessageItem(2, User(2, "会飞的鸟", R.drawable.girl_2), "这是第三天", mutableStateOf(false), "3小时前",mutableStateOf(0))
            )
        )
    )
    fun changeLikeNum(messageItem: MessageItem){
        messageItem.likeNum.value++
        messageItem.isLike.value=true
    }
    var isChatView by mutableStateOf(false)
    var chatView:MessageItem? by mutableStateOf(null)
    fun openFriendChat(messageItem: MessageItem){
        isChatView=true
        chatView=messageItem
    }
    fun closeFriendChat(){
        isChatView=false
    }
}