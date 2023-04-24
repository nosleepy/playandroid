package com.grandstream.playandroid.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.grandstream.playandroid.R
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.entity.BannerBean
import com.grandstream.playandroid.viewmodel.HomeViewModel
import com.grandstream.playandroid.widget.*

@ExperimentalPagerApi
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        TitleBar(title = "首页", icon = R.drawable.ic_search, onIconClick = {
            navController.navigate("search")
        })
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
//                    item {
//                        Text(text = "刷新中")
//                    }
                    itemsIndexed(viewModel.list) { index, item ->
                        when (item) {
                            is List<*> -> BannerItem(navController = navController, list = item as List<BannerBean>)
                            is ArticleBean -> {
                                ArticleItem(navController = navController, article = item, onCollectClick = { viewModel.collectOrCancel(item) })
                                Divider(modifier = Modifier.padding(16.dp, 0.dp), thickness = 0.5.dp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun BannerItem(navController: NavController, list: List<BannerBean>) {
    val dataList = list.map {
        BannerData(it.title, it.imagePath, it.url)
    }
    Banner(
        navController = navController,
        modifier = Modifier.fillMaxWidth().height(200.dp), dataList = dataList
    )
}