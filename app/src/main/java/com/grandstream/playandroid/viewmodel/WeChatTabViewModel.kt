package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class WeChatTabViewModel(private val scope: CoroutineScope, private val id: Long) {
    var pageState by mutableStateOf(LoadState.LOADING)
    var articleList by mutableStateOf(listOf<ArticleBean>())
    var loadState by mutableStateOf(false)
    var showLoading by mutableStateOf(false)
    private var page = 0

    init {
        firstLoad()
    }

    private fun firstLoad() {
        scope.launch {
            page = 0
            pageState = LoadState.LOADING
            val articleListRes = Api.get().getWeChatArticleList(id)
            if (articleListRes.isSuccess()) {
                pageState = LoadState.SUCCESS
                articleList = articleListRes.data!!.datas
            } else {
                pageState = LoadState.FAIL
            }
        }
    }

    fun onLoad() {
        scope.launch {
            loadState = true
            val articleListRes = Api.get().getWeChatArticleList(id, page + 1)
            if (articleListRes.isSuccess()) {
                page++
                articleList = articleList.toMutableList().apply {
                    addAll(articleListRes.data!!.datas)
                }
                loadState = false
            } else {
                loadState = false
            }
        }
    }

    fun collectOrCancel(article: ArticleBean) {
        scope.launch {
            showLoading = true
            CollectViewModel.collectOrCancel(article)
            showLoading = false
        }
    }
}