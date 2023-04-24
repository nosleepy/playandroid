package com.grandstream.playandroid.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grandstream.playandroid.R
import com.grandstream.playandroid.ui.theme.CustomColor
import com.grandstream.playandroid.widget.TitleBar

@Composable
fun OtherScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TitleBar(title = "其他")
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            OtherItemScreen(
                navController = navController,
                name = "项目",
                iconResId = R.drawable.ic_project,
                color = CustomColor.cranesbill,
                onClick = { navController.navigate("web?url=https://wanandroid.com/project") }
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
            OtherItemScreen(
                navController = navController,
                name = "体系",
                iconResId = R.drawable.ic_system,
                color = CustomColor.green,
                onClick = { navController.navigate("system") }
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
            OtherItemScreen(
                navController = navController,
                name = "问答",
                iconResId = R.drawable.ic_question,
                color = CustomColor.blue,
                onClick = { navController.navigate("web?url=https://wanandroid.com/wenda") }
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
            OtherItemScreen(
                navController = navController,
                name = "导航",
                iconResId = R.drawable.ic_navigation,
                color = CustomColor.fizz,
                onClick = { navController.navigate("web?url=https://wanandroid.com/navi") }
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
            OtherItemScreen(
                navController = navController,
                name = "网址",
                iconResId = R.drawable.ic_website,
                color = CustomColor.gall,
                onClick = { navController.navigate("web?url=https://wanandroid.com/index") }
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
            OtherItemScreen(
                navController = navController,
                name = "工具",
                iconResId = R.drawable.ic_tool,
                color = CustomColor.powder,
                onClick = { navController.navigate("tool") }
            )
            Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
        }
    }
}

@Composable
fun OtherItemScreen(navController: NavController, name: String, @DrawableRes iconResId: Int, color: Color, onClick: () -> Unit = {}) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .clickable { onClick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = painterResource(id = iconResId), contentDescription = null, tint = color, modifier = Modifier.size(26.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = name, fontSize = 14.sp)
        }
    }
}
