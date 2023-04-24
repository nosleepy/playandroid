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

class SquareViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var list by mutableStateOf(listOf<ArticleBean>())
    var refreshingState by mutableStateOf(false)
    var loadState by mutableStateOf(false)
    var showLoading by mutableStateOf(false)
    var page = 0

    var title by mutableStateOf("")
    var link by mutableStateOf("")

    init {
        firstLoad()
    }

    private fun firstLoad() {
        viewModelScope.launch {
            page = 0
            pageState = LoadState.LOADING
            val articleList = Api.get().getSquareArticleList()
            if (articleList.isSuccess()) {
                pageState = LoadState.SUCCESS
                list = articleList.data!!.datas
            } else {
                pageState = LoadState.FAIL
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            page = 0
            refreshingState = true
            val articleList = Api.get().getSquareArticleList()
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
            val articleRes = Api.get().getSquareArticleList(page + 1)
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

    fun shareArticle() {
        viewModelScope.launch {
            showLoading = true
            val res = Api.get().shareArticle(title, link)
//            Log.d("wlzhou", "res = $res")
            if (res.isSuccessIgnoreData()) {
                Toaster.show("分享成功")
            } else {
                Toaster.show(res.msg!!)
            }
            title = ""
            link = ""
            showLoading = false
        }
    }
}