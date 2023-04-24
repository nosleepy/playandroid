package com.grandstream.playandroid.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grandstream.playandroid.viewmodel.SearchResultViewModel
import com.grandstream.playandroid.widget.*

@Composable
fun SearchResultScreen(navController: NavController, keyword: String) {
    val viewModel: SearchResultViewModel = viewModel()
    viewModel.query(keyword)
    Column(modifier = Modifier
        .fillMaxSize()
//        .background(CustomColor.green)
    ) {
        TitleBar(title = viewModel.querykey, onBack = { navController.popBackStack() })
        PageLoading(
            loadState = viewModel.pageState,
            showLoading = viewModel.showLoading,
            onReload = { viewModel.firstLoad() },
        ) {
            SwipeRefreshAndLoadLayout(
                refreshingState = viewModel.refreshingState,
                loadState = viewModel.loadState,
                onRefresh = { viewModel.onRefresh() },
                onLoad = { viewModel.onLoad() }
            ) {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                ) {
                    itemsIndexed(viewModel.list) { index, item ->
                        ArticleItem(navController = navController, article = item, onCollectClick = { viewModel.collectOrCancel(item) })
                        Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                    }
                }
            }
        }
    }
}