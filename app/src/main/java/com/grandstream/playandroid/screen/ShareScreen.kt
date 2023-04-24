package com.grandstream.playandroid.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grandstream.playandroid.viewmodel.ShareViewModel
import com.grandstream.playandroid.widget.*

@Composable
fun ShareScreen(navController: NavController) {
    val viewModel: ShareViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "分享", onBack = { navController.popBackStack() })
        PageLoading(loadState = LoadState.SUCCESS) {
            SwipeRefreshAndLoadLayout(
                refreshingState = viewModel.refreshingState,
                loadState = viewModel.loadState,
                onRefresh = { viewModel.onRefresh() },
                onLoad = { viewModel.onLoad() }
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(viewModel.list) { index, item ->
                        ArticleItem(navController = navController, article = item, onDeleteClick = { viewModel.deletePrivateArticle(item) })
                        Divider(modifier = Modifier.padding(16.dp, 0.dp), thickness = 0.5.dp)
                    }
                }
            }
        }
    }
}