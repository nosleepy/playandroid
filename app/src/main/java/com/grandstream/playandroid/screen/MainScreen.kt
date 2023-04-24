package com.grandstream.playandroid.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import com.grandstream.playandroid.R

@ExperimentalPagerApi
@Composable
fun MainScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = 6, initialOffscreenLimit = 3)
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            when (page) {
                0 -> HomeScreen(navController = navController)
                1 -> SquareScreen(navController = navController)
                2 -> WeChatScreen(navController = navController)
                3 -> CourseScreen(navController = navController)
                4 -> OtherScreen(navController = navController)
                5 -> MineScreen(navController = navController)
            }
        }
        BottomNavigation(
            current = pagerState.currentPage,
            currentChanged = {
                scope.launch {
                    pagerState.scrollToPage(it)
                }
            }
        )
    }
}

@Composable
fun BottomNavigation(
    current: Int,
    currentChanged: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 0.dp, 4.dp, 0.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomNavigationItem(
            modifier = Modifier
                .weight(1f)
                .clickable { currentChanged(0) },
            iconResId = R.drawable.ic_home,
            label = "首页",
            tint = if (current == 0) Color.Red else Color.Gray
        )
        BottomNavigationItem(
            modifier = Modifier
                .weight(1f)
                .clickable { currentChanged(1) },
            iconResId = R.drawable.ic_square_line,
            label = "广场",
            tint = if (current == 1) Color.Red else Color.Gray,
        )
        BottomNavigationItem(
            modifier = Modifier
                .weight(1f)
                .clickable { currentChanged(2) },
            iconResId = R.drawable.ic_wechat,
            label = "公众号",
            tint = if (current == 2) Color.Red else Color.Gray,
        )
        BottomNavigationItem(
            modifier = Modifier
                .weight(1f)
                .clickable { currentChanged(3) },
            iconResId = R.drawable.ic_course,
            label = "教程",
            tint = if (current == 3) Color.Red else Color.Gray,
        )
        BottomNavigationItem(
            modifier = Modifier
                .weight(1f)
                .clickable { currentChanged(4) },
            iconResId = R.drawable.ic_other,
            label = "其他",
            tint = if (current == 4) Color.Red else Color.Gray,
        )
        BottomNavigationItem(
            modifier = Modifier
                .weight(1f)
                .clickable { currentChanged(5) },
            iconResId = R.drawable.ic_mine,
            label = "我的",
            tint = if (current == 5) Color.Red else Color.Gray,
        )
    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier,
    @DrawableRes iconResId: Int,
    label: String,
    tint: Color,
) {
    Column(
        modifier = modifier.padding(0.dp, 4.dp, 0.dp, 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = tint
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = tint
        )
    }
}