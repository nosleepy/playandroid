package com.grandstream.playandroid.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.grandstream.playandroid.ui.theme.CustomColor
import com.grandstream.playandroid.viewmodel.WeChatTabViewModel
import com.grandstream.playandroid.viewmodel.WeChatViewModel
import com.grandstream.playandroid.widget.ArticleItem
import com.grandstream.playandroid.widget.PageLoading
import com.grandstream.playandroid.widget.SwipeRefreshAndLoadLayout
import com.grandstream.playandroid.widget.TitleBar
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun WeChatScreen(navController: NavController) {
    val viewModel: WeChatViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "公众号")
        PageLoading(
            loadState = viewModel.pageState,
        ) {
            if (viewModel.authorList.isNotEmpty()) {
                val scope = rememberCoroutineScope()
                val pageState = rememberPagerState(pageCount = viewModel.authorList.size, initialOffscreenLimit = 3)
                Column(modifier = Modifier.fillMaxSize()) {
                    ScrollableTabRow(
                        selectedTabIndex = pageState.currentPage,
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = Color.White,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[pageState.currentPage]),
                                color = CustomColor.green
                            )
                        },
                        divider = {}
                    ) {
                        viewModel.authorList.forEachIndexed { index, authorBean ->
                            Tab(
                                modifier = Modifier.padding(vertical = 10.dp),
                                selected = (index == pageState.currentPage),
                                onClick = {
                                    scope.launch {
                                        pageState.scrollToPage(index)
                                    }
                                }
                            ) {
                                Text(text = authorBean.name, fontSize = 14.sp)
                            }
                        }
                    }
                    HorizontalPager(
                        state = pageState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        WeChatTab(navController = navController, viewModel = viewModel, id = viewModel.authorList[currentPage].id)
                    }
                }
            }
        }
    }
}

@Composable
fun WeChatTab(navController: NavController, viewModel: WeChatViewModel, id: Long) {
    var tabViewModel = viewModel.tabViewModelMap[id]
    if (tabViewModel == null) {
        tabViewModel = WeChatTabViewModel(viewModel.viewModelScope, id)
        viewModel.tabViewModelMap.put(id, tabViewModel)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        PageLoading(
            loadState = tabViewModel.pageState,
            showLoading = tabViewModel.showLoading,
        ) {
            SwipeRefreshAndLoadLayout(
                refreshingState = false,
                loadState = tabViewModel.loadState,
                onRefresh = { },
                onLoad = { tabViewModel.onLoad() }
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(tabViewModel.articleList) { index, item ->
                        ArticleItem(navController = navController, article = item, onCollectClick = { tabViewModel.collectOrCancel(item) })
                        Divider(modifier = Modifier.padding(16.dp, 0.dp), thickness = 0.5.dp)
                    }
                }
            }
        }
    }
}