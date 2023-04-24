package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.util.Toaster
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.launch

class ShareViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var list by mutableStateOf(listOf<ArticleBean>())
    var refreshingState by mutableStateOf(false)
    var loadState by mutableStateOf(false)
    var showLoading by mutableStateOf(false)
    var page = 1

    init {
        firstLoad()
    }

    private fun firstLoad() {
        viewModelScope.launch {
            page = 1
            pageState = LoadState.LOADING
            val res = Api.get().getPrivateArticleList()
            if (res.isSuccess()) {
                pageState = LoadState.SUCCESS
                list = res.data!!.shareArticles.datas
            } else {
                pageState = LoadState.FAIL
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            page = 1
            refreshingState = true
            val res = Api.get().getPrivateArticleList()
            if (res.isSuccess()) {
                list = res.data!!.shareArticles.datas
                refreshingState = false
            } else {
                refreshingState = false
            }
        }
    }

    fun onLoad() {
        viewModelScope.launch {
            loadState = true
            val res = Api.get().getPrivateArticleList(page + 1)
            if (res.isSuccess()) {
                page++
                list = list.toMutableList().apply {
                    addAll(res.data!!.shareArticles.datas)
                }
                loadState = false
            } else {
                loadState = false
            }
        }
    }

    fun deletePrivateArticle(article: ArticleBean) {
        viewModelScope.launch {
            showLoading = true
            val res = Api.get().deletePrivateArticle(article.id)
            if (res.isSuccessIgnoreData()) {
                Toaster.show("删除成功")
                onRefresh()
            } else {
                Toaster.show("删除失败")
            }
            showLoading = false
        }
    }
}