package com.me.jkcomposebeta

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.me.jkcompose.JKViewModel
import com.me.jkcompose.ui.theme.JKComposeTheme
import com.me.jkcompose.ui.theme.green1
import com.me.jkcomposebeta.ui.view.Home
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    val viewModel: JKViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JKComposeTheme {
                MainView()
            }
        }
    }

    override fun onBackPressed() {
        if (viewModel.isChatView) {
            viewModel.closeFriendChat()
        } else {
            super.onBackPressed()
        }
    }
}

@Composable
private fun MainView() {
    Box {
        Home()
        ChatPage()
    }
}


@Composable
fun DesignMainTest() {
    MainView()
}

@Composable
fun ChatPage() {
    val viewModel: JKViewModel = viewModel()
    val percentOffsetX = animateFloatAsState(if (viewModel.isChatView) 0f else 1f)
    Box(
        Modifier
            //必须写在Modifier所有方法之前，不然不会执行自定义测量
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints = constraints)
                layout(placeable.width, placeable.height) {
                    val offset=(percentOffsetX.value * placeable.width).roundToInt()
                    //TODO 暂时这样写，之后更新后改回动画模式
//                    val offset = if (viewModel.isChatView) 0 else placeable.width
                    placeable.placeRelative(offset, 0)
                }
            }
            .fillMaxSize()
            .background(green1)
    )
}