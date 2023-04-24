package com.grandstream.playandroid.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.grandstream.playandroid.viewmodel.ArticleListViewModel
import com.grandstream.playandroid.widget.*

@ExperimentalPagerApi
@Composable
fun ArticleListScreen(navController: NavController, id: Long) {
    val viewModel: ArticleListViewModel = viewModel()
    viewModel.query(id)
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "文章列表", onBack = { navController.popBackStack() })
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
        }
    }
}