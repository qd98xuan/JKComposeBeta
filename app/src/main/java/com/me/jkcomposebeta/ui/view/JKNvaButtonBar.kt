package com.me.jkcomposebeta

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.me.jkcompose.JKViewModel
import com.me.jkcompose.ui.theme.grey300
import com.me.jkcompose.ui.theme.white1
import com.me.jkcompose.ui.theme.yellow200

@Composable
fun NavButtonBar(selected: Int) {
    val viewModel: JKViewModel = viewModel()
    //TODO 需要修改主题颜色
    Row(Modifier.background(white1)) {
        NavButtonIcon(
            if (selected == 0) com.me.jkcomposebeta.R.drawable.friend_inline else com.me.jkcomposebeta.R.drawable.friend_outline,
            //TODO 需要修改主题颜色
            "动态",
            Modifier
                .clickable {
                    viewModel.selectedTab = 0
                }
                .weight(1f), if (selected == 0) yellow200 else grey300
        )
        NavButtonIcon(
            if (selected == 1) com.me.jkcomposebeta.R.drawable.search_inline else com.me.jkcomposebeta.R.drawable.search_outline,
            //TODO 需要修改主题颜色
            "发现",
            Modifier
                .clickable {
                    viewModel.selectedTab = 1
                }
                .weight(1f), if (selected == 1) yellow200 else grey300
        )
        NavButtonIcon(
            if (selected == 2) com.me.jkcomposebeta.R.drawable.message_inline else com.me.jkcomposebeta.R.drawable.message_outline,
            //TODO 需要修改主题颜色
            "通知",
            Modifier
                .clickable {
                    viewModel.selectedTab = 2
                }
                .weight(1f), if (selected == 2) yellow200 else grey300
        )
        NavButtonIcon(
            if (selected == 3) com.me.jkcomposebeta.R.drawable.me_inline else com.me.jkcomposebeta.R.drawable.me_outline,
            //TODO 需要修改主题颜色
            "我",
            Modifier
                .clickable {
                    viewModel.selectedTab = 3
                }
                .weight(1f), if (selected == 3) yellow200 else grey300
        )

    }
}

@Composable
private fun NavButtonIcon(iconId: Int, title: String, modifier: Modifier = Modifier, tint: Color) {
    Column(
        modifier.padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            vectorResource(iconId),
            "",
            modifier
                .size(27.dp), tint = tint
        )
        Text(title, fontSize = 13.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun DesignTest() {
    Box(Modifier.height(80.dp)) {
        NavButtonBar(1)
    }
}