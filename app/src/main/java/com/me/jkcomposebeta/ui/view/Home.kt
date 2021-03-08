package com.me.jkcomposebeta.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.me.jkcompose.JKViewModel
import com.me.jkcomposebeta.FriendViewList
import com.me.jkcomposebeta.NavButtonBar
import com.rengwuxian.wecompose.ui.Pager
import com.rengwuxian.wecompose.ui.PagerState

@Composable
fun Home() {
    Column {
        val viewModel: JKViewModel = viewModel()
        val pagerState: PagerState = run {
            val clock = AmbientAnimationClock.current
            remember(clock) { PagerState(clock, maxPage = 3) }
        }
        Pager(state = pagerState, Modifier.weight(1f)) {
            when (page) {
                0 -> FriendViewList(viewModel.messageData.messages)
                1 -> Box(Modifier.fillMaxSize())
                2 -> Box(Modifier.fillMaxSize())
                3 -> Box(Modifier.fillMaxSize())
            }
        }
        Box(Modifier.height(50.dp)) {
            NavButtonBar(viewModel.selectedTab)
        }
    }
}