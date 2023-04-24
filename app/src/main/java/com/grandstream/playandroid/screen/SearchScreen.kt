package com.grandstream.playandroid.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.grandstream.playandroid.R
import com.grandstream.playandroid.util.Toaster
import com.grandstream.playandroid.viewmodel.SearchViewModel

@Composable
fun SearchScreen(navController: NavController) {
    val viewModel: SearchViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp).padding(horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "返回",
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(18.dp)
                    .clickable { navController.popBackStack() }
            )
            BasicTextField(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                value = viewModel.keyword,
                onValueChange = { viewModel.keyword = it },
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "清除",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(14.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .clickable { viewModel.keyword = "" }
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "搜索",
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(18.dp)
                    .clickable {
                        if (viewModel.keyword.isEmpty()) {
                            Toaster.show("请输入关键字")
                        } else {
                            viewModel.addHistory(viewModel.keyword)
                            navController.navigate("search_result?keyword=${viewModel.keyword}")
                        }
                    }
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()
//            .background(CustomColor.addicted)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "热门搜索",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp,
            ) {
                viewModel.hotKeys.forEach {
                    Box(
                        modifier = Modifier
                            .background(color = Color(0xFFDDDDDD), shape = RoundedCornerShape(percent = 30))
                            .clickable {
                                viewModel.keyword = it.name
                                viewModel.addHistory(viewModel.keyword)
                                navController.navigate("search_result?keyword=${viewModel.keyword}")
                            }
                    ) {
                        Text(text = it.name, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontSize = 14.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "搜索历史",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(viewModel.history) { index, item ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.keyword = item
                            navController.navigate("search_result?keyword=${viewModel.keyword}")
                        }) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
//                            .background(CustomColor.tree)
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = item,
                                modifier = Modifier.weight(1f),
                                fontSize = 14.sp,
                                maxLines = 1,
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete),
                                contentDescription = "",
                                modifier = Modifier.size(12.dp).clickable { viewModel.removeHistory(item) }
                            )
                        }
                    }
                }
            }
        }
    }
}