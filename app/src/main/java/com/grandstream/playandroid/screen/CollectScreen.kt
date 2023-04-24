package com.grandstream.playandroid.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grandstream.playandroid.viewmodel.CollectViewModel
import com.grandstream.playandroid.widget.CollectItem
import com.grandstream.playandroid.widget.PageLoading
import com.grandstream.playandroid.widget.SwipeRefreshAndLoadLayout
import com.grandstream.playandroid.widget.TitleBar
import com.grandstream.playandroid.R
import com.grandstream.playandroid.ui.theme.CustomColor

@Composable
fun CollectScreen(navController: NavController) {
    val viewModel: CollectViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(
            title = "收藏",
            onBack = { navController.popBackStack() },
            icon = R.drawable.ic_collect,
            onIconClick = {
                viewModel.showDialog = true
                viewModel.addMode = true
                viewModel.clearInputInfo()
            },
        )
        PageLoading(
            loadState = viewModel.pageState,
            showLoading = viewModel.showLoading,
        ) {
            SwipeRefreshAndLoadLayout(
                refreshingState = viewModel.refreshingState,
                loadState = viewModel.loadState,
                onRefresh = { viewModel.onRefresh() },
                onLoad = { viewModel.onLoad() }
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
                    itemsIndexed(viewModel.collectList) { index, item ->
                        CollectItem(
                            navController = navController,
                            collect = item,
                            onUnCollectClick = { viewModel.unCollect(item) },
                            onEditCollectClick = {
                                viewModel.showDialog = true
                                viewModel.editMode = true
                                viewModel.collectId = item.id
                                viewModel.showInputInfo(item)
                            },
                        )
                        Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                    }
                }
            }
            if (viewModel.showDialog) {
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {
                        TextButton(
                            onClick = {
                                viewModel.showDialog = false
                                if (viewModel.addMode) {
                                    viewModel.addCollectArticle()
                                } else {
                                    viewModel.editCollect()
                                }
                                viewModel.addMode = false
                                viewModel.editMode = false
                            },
                        ) {
                            Text(text = "确认", color = CustomColor.cranesbill)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                viewModel.showDialog = false
                                viewModel.addMode = false
                                viewModel.editMode = false
                            },
                        ) {
                            Text(text = "取消", color = CustomColor.green)
                        }
                    },
                    title = { Text(text = if (viewModel.addMode) "收藏文章" else "编辑文章") },
                    text = {
                        Column(modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "文章标题")
                                TextField(
                                    value = viewModel.title,
                                    onValueChange = { viewModel.title = it },
                                    modifier = Modifier.weight(1f),
                                    colors = TextFieldDefaults.outlinedTextFieldColors()
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "文章作者")
                                TextField(
                                    value = viewModel.anthor,
                                    onValueChange = { viewModel.anthor = it },
                                    modifier = Modifier.weight(1f),
                                    colors = TextFieldDefaults.outlinedTextFieldColors()
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "文章链接")
                                TextField(
                                    value = viewModel.link,
                                    onValueChange = { viewModel.link = it },
                                    modifier = Modifier.weight(1f),
                                    colors = TextFieldDefaults.outlinedTextFieldColors()
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}