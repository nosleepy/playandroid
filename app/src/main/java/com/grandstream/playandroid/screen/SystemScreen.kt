package com.grandstream.playandroid.screen

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
import com.google.accompanist.flowlayout.FlowRow
import com.grandstream.playandroid.entity.SystemBean
import com.grandstream.playandroid.viewmodel.SystemViewModel
import com.grandstream.playandroid.widget.PageLoading
import com.grandstream.playandroid.widget.TitleBar

@Composable
fun SystemScreen(navController: NavController) {
    val viewModel: SystemViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "体系", onBack = { navController.popBackStack() })
        PageLoading(loadState = viewModel.pageState) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(viewModel.list) { index, item ->
                    SystemItemScreen(navController = navController, system = item)
                    Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun SystemItemScreen(navController: NavController, system: SystemBean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = system.name, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp,
        ) {
            system.children.forEach { item ->
                Text(
                    text = item.name,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.clickable {
                        navController.navigate("article_list?id=${item.id}")
                    }
                )
            }
        }
    }
}