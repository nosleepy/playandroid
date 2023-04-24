package com.grandstream.playandroid.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.grandstream.playandroid.viewmodel.SquareViewModel
import com.grandstream.playandroid.widget.ArticleItem
import com.grandstream.playandroid.widget.PageLoading
import com.grandstream.playandroid.widget.SwipeRefreshAndLoadLayout
import com.grandstream.playandroid.widget.TitleBar
import com.grandstream.playandroid.R
import com.grandstream.playandroid.ui.theme.CustomColor

@ExperimentalPagerApi
@Composable
fun SquareScreen(navController: NavController) {
    val viewModel: SquareViewModel = viewModel()
    var showDialog by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "广场", icon = R.drawable.ic_share, onIconClick = { showDialog = true })
        PageLoading(
            loadState = viewModel.pageState,
            showLoading = viewModel.showLoading
        ) {
            SwipeRefreshAndLoadLayout(
                refreshingState = viewModel.refreshingState,
                loadState = viewModel.loadState,
                onRefresh = { viewModel.onRefresh() },
                onLoad = { viewModel.onLoad() },
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(viewModel.list) { index, item ->
                        ArticleItem(navController = navController, article = item, onCollectClick = {viewModel.collectOrCancel(item)})
                        Divider(modifier = Modifier.padding(16.dp, 0.dp), thickness = 0.5.dp)
                    }
                }
            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showDialog = false
                                viewModel.shareArticle()
                            },
                        ) {
                            Text(text = "确认", color = CustomColor.cranesbill)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDialog = false
                            },
                        ) {
                            Text(text = "取消", color = CustomColor.green)
                        }
                    },
                    title = { Text(text = "分享文章") },
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