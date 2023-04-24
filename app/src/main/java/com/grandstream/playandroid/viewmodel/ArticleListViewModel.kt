package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.launch

class ArticleListViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var list by mutableStateOf(listOf<ArticleBean>())
    var refreshingState by mutableStateOf(false)
    var loadState by mutableStateOf(false)
    var showLoading by mutableStateOf(false)
    var page = 0
    private var queryId: Long = 0

    fun query(id: Long) {
        if (queryId == 0L) {
            queryId = id
            firstLoad()
        }
    }

    private fun firstLoad() {
        viewModelScope.launch {
            page = 0
            pageState = LoadState.LOADING
            val res = Api.get().getSystemArticleList(page, queryId)
            if (res.isSuccess()) {
                pageState = LoadState.SUCCESS
                list = res.data!!.datas
            } else {
                pageState = LoadState.FAIL
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            page = 0
            refreshingState = true
            val articleList = Api.get().getSystemArticleList(page, queryId)
            if (articleList.isSuccess()) {
                list = articleList.data!!.datas
                refreshingState = false
            } else {
                refreshingState = false
            }
        }
    }

    fun onLoad() {
        viewModelScope.launch {
            loadState = true
            val articleRes = Api.get().getSystemArticleList(page + 1, queryId)
            if (articleRes.isSuccess()) {
                page++
                list = list.toMutableList().apply {
                    addAll(articleRes.data!!.datas)
                }
                loadState = false
            } else {
                loadState = false
            }
        }
    }

    fun collectOrCancel(article: ArticleBean) {
        viewModelScope.launch {
            showLoading = true
            CollectViewModel.collectOrCancel(article)
            showLoading = false
        }
    }
}