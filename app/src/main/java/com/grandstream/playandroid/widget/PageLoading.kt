package com.grandstream.playandroid.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.grandstream.playandroid.ui.theme.CustomColor

enum class LoadState {
    LOADING,
    SUCCESS,
    FAIL,
    EMPTY,
}

@Composable
fun PageLoading(
    modifier: Modifier = Modifier,
    loadState: LoadState = LoadState.SUCCESS,
    onReload: () -> Unit = {},
    showLoading: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (loadState) {
            LoadState.LOADING -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = CustomColor.cranesbill,
            )
            LoadState.SUCCESS -> {
                content()
                if (showLoading) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0x80000000))
                        .clickable { }) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White,
                        )
                    }
                }
            }
            LoadState.FAIL -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable { onReload() }) {
                    Text(
                        text = "加载失败，点击重试",
                        modifier = Modifier.align(alignment = Alignment.Center)
                    )
                }
            }
            LoadState.EMPTY -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable { onReload() }) {
                    Text(
                        text = "这里什么都没有",
                        modifier = Modifier.align(alignment = Alignment.Center)
                    )
                }
            }
        }
    }
}