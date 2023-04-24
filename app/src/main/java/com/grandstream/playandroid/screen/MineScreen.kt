package com.grandstream.playandroid.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.grandstream.playandroid.R
import com.grandstream.playandroid.ui.theme.CustomColor
import com.grandstream.playandroid.util.AuthManager
import com.grandstream.playandroid.viewmodel.MineViewModel
import com.grandstream.playandroid.widget.PageLoading

@Composable
fun MineScreen(navController: NavController) {
    val viewModel: MineViewModel = viewModel()
    PageLoading(showLoading = viewModel.showLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clickable {
                        if (viewModel.user == null) {
                            navController.navigate("login")
                        }
                    },
                painter = painterResource(id = R.drawable.ic_jetpack),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = viewModel.user?.userInfo?.username ?: "未登录",
                color = Color.Black,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Box(modifier = Modifier.background(color =  Color(0xFFFFA2A2))) {
                    Text(
                        text = "LV" + (viewModel.user?.coinInfo?.level ?: ""),
                        style = TextStyle(color = Color.White),
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                        fontSize = 12.sp,
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box(modifier = Modifier.background(color =  Color(0xFFFFAF95))) {
                    Text(
                        text = "排名" + (viewModel.user?.coinInfo?.rank ?: ""),
                        style = TextStyle(color = Color.White),
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Divider(color = Color(0xFFDFD7D7), thickness = 0.5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(color = Color.White)
                    .clickable {
                        if (viewModel.user == null) {
                            navController.navigate("login")
                        }
                    },
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "我的昵称",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Text(
                    text = if (AuthManager.isLogin()) viewModel.user!!.userInfo.nickname else "",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Divider(color = Color(0xFFDFD7D7), thickness = 0.5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(color = Color.White)
                    .clickable {
                        if (viewModel.user == null) {
                            navController.navigate("login")
                        }
                    },
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "我的积分",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Text(
                    text = if (AuthManager.isLogin()) viewModel.user!!.coinInfo.coinCount.toString() else "",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 15.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Divider(color = Color(0xFFDFD7D7), thickness = 0.5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color.White)
                    .clickable {
                        if (viewModel.user == null) {
                            navController.navigate("login")
                        } else {
                            navController.navigate("collect")
                        }
                    },
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "我的收藏",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Text(
                    text = if (AuthManager.isLogin()) viewModel.user?.collectArticleInfo?.count.toString() else "",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Divider(color = Color(0xFFDFD7D7), thickness = 0.5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(color = Color.White)
                    .clickable {
                        if (viewModel.user == null) {
                            navController.navigate("login")
                        } else {
                            navController.navigate("share")
                        }
                    },
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "我的分享",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Divider(color = Color(0xFFDFD7D7), thickness = 0.5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(color = Color.White)
                    .clickable {
                        if (viewModel.user == null) {
                            navController.navigate("login")
                        }
                    },
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "我的邮箱",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Text(
                    text = if (AuthManager.isLogin()) viewModel.user?.userInfo!!.email else "",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Divider(color = Color(0xFFDFD7D7), thickness = 0.5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(color = Color.White)
                    .clickable {
                        if (viewModel.user == null) {
                            navController.navigate("login")
                        }
                    },
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "上次登录时间",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    fontSize = 14.sp,
                )
                Text(
                    text = if (AuthManager.isLogin()) viewModel.loginTime!! else "",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Divider(color = Color(0xFFDFD7D7), thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(26.dp))
            if (viewModel.user != null) {
                Button(
                    onClick = { viewModel.showDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(percent = 18),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = CustomColor.cranesbill,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "退出登录", fontSize = 14.sp)
                }
            }
            if (viewModel.showDialog) {
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {
                        TextButton(
                            onClick = {
                                viewModel.showDialog = false
                                viewModel.logout()
                            },
                        ) {
                            Text(text = "确认", color = CustomColor.cranesbill)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                viewModel.showDialog = false
                            },
                        ) {
                            Text(text = "取消", color = CustomColor.green)
                        }
                    },
                    title = { Text(text = "提示") },
                    text = { Text(text = "确认退出登录") }
                )
            }
        }
    }
}