package com.grandstream.playandroid.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.grandstream.playandroid.entity.CourseBean
import com.grandstream.playandroid.ui.theme.CustomColor
import com.grandstream.playandroid.viewmodel.CourseViewModel
import com.grandstream.playandroid.widget.PageLoading
import com.grandstream.playandroid.widget.TitleBar

@Composable
fun CourseScreen(navController: NavController) {
    val viewModel: CourseViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "教程")
        PageLoading(loadState = viewModel.pageState) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(viewModel.list) { index, item ->
                    CourseItemScreen(navController = navController, course = item)
                    Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun CourseItemScreen(navController: NavController, course: CourseBean) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .clickable { navController.navigate("chapter?id=${course.id}") }) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberCoilPainter(request = course.cover),
                contentDescription = null,
                modifier = Modifier.width(80.dp).height(120.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = course.name, fontSize = 14.sp, color = CustomColor.cranesbill)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = course.author, fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = course.desc.trim(), fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}