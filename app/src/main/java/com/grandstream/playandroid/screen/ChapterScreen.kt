package com.grandstream.playandroid.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.viewmodel.ChapterViewModel
import com.grandstream.playandroid.widget.LoadState
import com.grandstream.playandroid.widget.PageLoading
import com.grandstream.playandroid.widget.TitleBar

@Composable
fun ChapterScreen(navController: NavController, id: Long) {
    val viewModel: ChapterViewModel = viewModel()
    viewModel.query(id)
    Log.d("wlzhou", "chapter id = $id")
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "目录", onBack = { navController.popBackStack() })
        PageLoading(loadState = viewModel.pageState) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(viewModel.list) { index, item ->
                    ChapterItemScreen(navController = navController, article = item, index = (index + 1))
                    Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun ChapterItemScreen(navController: NavController, article: ArticleBean, index: Int) {
    Box(modifier = Modifier.fillMaxWidth().clickable { navController.navigate("web?url=${article.link}") }) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .background(Color.White)
        ) {
            Text(text = index.toString(), fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = article.title, fontSize = 14.sp)
        }
    }
}