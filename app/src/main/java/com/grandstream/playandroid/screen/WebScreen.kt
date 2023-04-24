package com.grandstream.playandroid.screen

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grandstream.playandroid.ui.theme.CustomColor
import com.grandstream.playandroid.viewmodel.WebViewModel
import com.grandstream.playandroid.widget.TitleBar

@Composable
fun WebScreen(navController: NavController, url: String) {
    val viewModel: WebViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = viewModel.title, onBack = { navController.popBackStack() })
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                            if (url == null) {
                                return false
                            }
                            view?.loadUrl(url)
                            return true
                        }
                    }
                    webChromeClient = object : WebChromeClient() {
                        override fun onReceivedTitle(view: WebView?, title: String?) {
                            viewModel.title = title ?: ""
                        }

                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            viewModel.progress = newProgress
                        }
                    }
                    loadUrl(url)
                }
            })
            if (viewModel.progress < 100) {
                LinearProgressIndicator(
                    progress = viewModel.progress / 100f,
                    modifier = Modifier.fillMaxWidth().height(2.dp),
                    color = CustomColor.green,
                )
            }
        }
    }
}