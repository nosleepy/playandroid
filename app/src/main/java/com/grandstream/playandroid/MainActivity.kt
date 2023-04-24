package com.grandstream.playandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.grandstream.playandroid.screen.NavigationScreen
import com.grandstream.playandroid.ui.theme.PlayAndroidTheme

@ExperimentalFoundationApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayAndroidTheme {
                NavigationScreen()
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlayAndroidTheme {
        NavigationScreen()
    }
}