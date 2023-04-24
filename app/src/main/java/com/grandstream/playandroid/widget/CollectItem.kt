package com.grandstream.playandroid.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.grandstream.playandroid.entity.CollectBean
import com.grandstream.playandroid.ui.theme.CustomColor

@Composable
fun CollectItem(
    navController: NavController,
    collect: CollectBean,
    onUnCollectClick: () -> Unit = {},
    onEditCollectClick: (collect: CollectBean) -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxWidth().background(color = Color.White).clickable { navController.navigate("web?url=${collect.link}") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (collect.envelopePic.isNotEmpty()) {
                Image(
                    painter = rememberCoilPainter(request = collect.envelopePic),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = collect.author.ifEmpty { "匿名用户" },
                        modifier = Modifier.weight(1f),
                        color = Color.Gray,
                        fontSize = 12.sp,
                    )
                    Text(text = collect.niceDate, color = Color.Gray, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = collect.title, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = collect.chapterName.ifEmpty { "未分类" },
                        modifier = Modifier.weight(1f).align(alignment = Alignment.CenterVertically),
                        color = Color.Gray,
                        fontSize = 12.sp,
                    )
                    Icon(
                        imageVector = Icons.Sharp.Favorite,
                        contentDescription = null,
                        tint = CustomColor.cranesbill,
                        modifier = Modifier.size(18.dp).clickable { onUnCollectClick() }
                    )
                    if (collect.originId == -1L) {
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            imageVector = Icons.Sharp.Edit,
                            contentDescription = null,
                            tint = CustomColor.green,
                            modifier = Modifier.size(18.dp).clickable { onEditCollectClick(collect) }
                        )
                    }
                }
            }
        }
    }
}