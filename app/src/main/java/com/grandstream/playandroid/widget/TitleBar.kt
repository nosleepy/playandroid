package com.grandstream.playandroid.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grandstream.playandroid.R

@Composable
fun TitleBar(
    title: String,
    onBack: (() -> Unit)? = null,
    @DrawableRes icon: Int? = null,
    onIconClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        if (onBack != null) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(18.dp)
                    .clickable { onBack() },
                contentDescription = "返回",
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        Text(
            text = title,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .weight(1f),
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (icon != null) {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(18.dp)
                    .clickable { onIconClick?.invoke() },
                painter = painterResource(id = icon),
                contentDescription = "",
            )
        }
    }
}