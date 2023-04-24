package com.grandstream.playandroid.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.ui.theme.CustomColor
import com.grandstream.playandroid.util.AuthManager
import com.grandstream.playandroid.util.Toaster

@Composable
fun ArticleItem(
    navController: NavController,
    article: ArticleBean,
    onCollectClick: (() -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .clickable { navController.navigate("web?url=${article.link}") }) {
        Column(modifier = Modifier.padding(16.dp, 10.dp, 16.dp, 10.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                article.tags.forEach {
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .border(0.5.dp, it.getColor(), RoundedCornerShape(3.dp))
                            .padding(2.dp, 1.dp, 2.dp, 1.dp),
                        color = it.getColor(),
                        fontSize = 10.sp,
                    )
                    Spacer(modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .width(8.dp)
                        .height(0.dp))
                }
                Text(
                    text = article.author.ifEmpty { article.shareUser },
                    modifier = Modifier
                        .weight(1f)
                        .align(alignment = Alignment.CenterVertically),
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .width(10.dp)
                    .height(0.dp))
                Text(
                    text = article.niceShareDate,
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    fontSize = 12.sp,
                )
            }
            Text(
                text = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                fontSize = 14.sp
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)) {
                Text(
                    text = "${article.superChapterName}/${article.chapterName}",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
                if (onCollectClick != null) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .clickable {
                                if (AuthManager.isLogin()) {
                                    onCollectClick.invoke()
                                } else {
                                    navController.navigate("login")
                                    Toaster.show("请先登录~")
                                }
                            },
                        imageVector = if (article.collect) Icons.Sharp.Favorite else Icons.Sharp.FavoriteBorder,
                        contentDescription = null,
                        tint = if (article.collect) CustomColor.cranesbill else Color.Gray,
                    )
                }
                if (onDeleteClick != null) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .clickable {
                                onDeleteClick.invoke()
                            },
                        imageVector = Icons.Sharp.Delete,
                        contentDescription = null,
                        tint = CustomColor.cranesbill,
                    )
                }
            }
        }
    }
}