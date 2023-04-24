package com.grandstream.playandroid.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grandstream.playandroid.ui.theme.CustomColor
import com.grandstream.playandroid.viewmodel.ToolViewModel
import com.grandstream.playandroid.widget.TitleBar

@ExperimentalFoundationApi
@Composable
fun ToolScreen(navController: NavController) {
    val viewModel: ToolViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "工具", onBack = { navController.popBackStack() })
        LazyVerticalGrid(cells = GridCells.Fixed(2), modifier = Modifier.padding(horizontal = 16.dp)) {
            items(viewModel.list) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(68.dp)
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                        .background(Color.White)
                        .border(1.dp, CustomColor.blush)
                        .clickable { navController.navigate("web?url=${item.link}") },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = item.name, fontSize = 14.sp)
                    Text(
                        text = item.desc,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}