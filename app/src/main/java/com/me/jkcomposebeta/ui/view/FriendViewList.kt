package com.me.jkcomposebeta

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.me.jkcompose.JKViewModel
import com.me.jkcompose.data.MessageItem
import com.me.jkcompose.ui.theme.*
import com.me.jkcomposebeta.R

@Composable
fun FriendViewList(messages: List<MessageItem>) {
    Column {
        AppBar()
        FriendListView(messages)
    }
}

@Composable
private fun AppBar() {
    val iconSize = 20.dp
    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        //TODO 需要修改为系统颜色
        Icon(
            imageVector = vectorResource(id = R.drawable.camera),
            contentDescription = "",
            tint = grey3,
            modifier =
            Modifier
                .size(iconSize)
                .weight(1f)
        )
        Box(
            Modifier
                .weight(3f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Text(text = "动态", color = grey3, fontSize = 15.sp)
        }
        Icon(
            imageVector = vectorResource(id = R.drawable.add_friend), "", tint = grey3,
            modifier = Modifier
                .size(iconSize)
                .weight(1f)
        )
    }
    Divider(color = grey4, startIndent = 0.dp, thickness = 1.dp)
}

@Composable
private fun FriendListView(
    messages: List<MessageItem>
) {
    val viewModel: JKViewModel = viewModel()
    LazyColumn(
        Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(messages) { index, message ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .clickable {
                        //TODO 暂时添加
                        viewModel.openFriendChat(message)
                    }, horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .weight(1f)
                ) {
                    Image(
                        imageVector = vectorResource(id =message.user.headImg),
                        "",
                        modifier = Modifier
                            .online(true, green300)//TODO 更换为主题颜色
                            .size(48.dp)
                            .clip(CircleShape)
                            .border(
                                shape = CircleShape,
                                width = 2.dp,
                                brush = Brush.linearGradient(
                                    listOf(yellow200, teal200), Offset(0f, 0f),
                                    Offset(48f, 48f)
                                )
                            )
                            .border(
                                shape = CircleShape,
                                width = 4.dp,
                                brush = SolidColor(Color.White)
                            )
                    )
                }
                Column(Modifier.weight(4f)) {
                    Column {
                        Box(modifier = Modifier.height(20.dp)) {
                            Text(text = message.user.name, fontSize = 15.sp)
                        }
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .padding(vertical = 10.dp)
                        ) {
                            Text(
                                text = message.time,
                                fontSize = 10.sp,
                                color = grey1
                            )
                        }
                    }
                    Column(modifier = Modifier) {
                        Text(text = message.content)
                        InteractionButtons(message)
                    }
                }
            }
            ListDivideView(index, messages)
        }
    }
}

@Composable
private fun ListDivideView(
    index: Int,
    messages: List<MessageItem>
) {
    if (index < messages.size - 1) {
        Divider(color = grey3, startIndent = 35.dp, thickness = 1.dp)
    }
}

@Composable
private fun InteractionButtons(message: MessageItem) {
    val viewModel: JKViewModel = viewModel()
    val iconsSize = 18.dp
    Box(Modifier.padding(top = 18.dp)) {
        //TODO 更换主题色彩
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = vectorResource(id = R.drawable.like),
                    "",
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(iconsSize)
                        .clickable {
                            viewModel.changeLikeNum(message)
                        },
                    tint = if (message.isLike.value) red1 else grey3
                )
                Text(
                    text = message.likeNum.value.toString(),
                    fontSize = 15.sp,
                    color = if (message.isLike.value) red1 else grey3
                )
            }
            Icon(
                vectorResource(R.drawable.comment),
                "",
                Modifier
                    .size(iconsSize)
                    .weight(1f),
                tint = grey3
            )
            Icon(
                vectorResource(R.drawable.share),
                "",
                Modifier
                    .size(iconsSize)
                    .weight(1f),
                tint = grey3
            )
            Icon(
                vectorResource(R.drawable.others),
                "",
                Modifier
                    .size(iconsSize)
                    .weight(1f),
                tint = grey3
            )
        }
    }
}


fun Modifier.online(show: Boolean, badge: Color): Modifier = composed{
    drawWithContent {
        drawContent()
        drawIntoCanvas {
            val paint = Paint().apply {
                color = badge
            }
            if (show) {
                it.drawCircle(
                    Offset(size.width - 1.dp.toPx(), size.height - 1.dp.toPx()),
                    4.dp.toPx(),
                    paint
                )
            }
        }
    }
}
