package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.entity.TagBean
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var list by mutableStateOf(listOf<Any>())
    var refreshingState by mutableStateOf(false)
    var loadState by mutableStateOf(false)
    var showLoading by mutableStateOf(false)
    var page = 0

    init {
        firstLoad()
    }

    fun firstLoad() {
        viewModelScope.launch {
            pageState = LoadState.LOADING
            val bannerDeffer = async { Api.get().getHomeBanner() }
            val stickyDeffer = async { Api.get().getStickyArticle() }
            val articleDeffer = async { Api.get().getHomeArticleList() }
            val bannerRes = bannerDeffer.await()
            val stickyRes = stickyDeffer.await()
            val articleRes = articleDeffer.await()
            if (bannerRes.isSuccess() && stickyRes.isSuccess()) {
//                Log.d("wlzhou", "stickyRes size = ${stickyRes.data!!.size}")
                pageState = LoadState.SUCCESS
                list = mutableListOf<Any>().apply {
                    add(bannerRes.data!!)
                    addAll(stickyRes.data!!.onEach {
                        it.tags.add(0, TagBean("置顶"))
                    })
                    addAll(articleRes.data!!.datas)
                }
            } else {
                pageState = LoadState.FAIL
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            page = 0;
            refreshingState = true
            val bannerDeffer = async { Api.get().getHomeBanner() }
            val stickyDeffer = async { Api.get().getStickyArticle() }
            val articleDeffer = async { Api.get().getHomeArticleList() }
            val bannerRes = bannerDeffer.await()
            val stickyRes = stickyDeffer.await()
            val articleRes = articleDeffer.await()
            if (bannerRes.isSuccess() && stickyRes.isSuccess()) {
//                Log.d("wlzhou", "stickyRes size = ${stickyRes.data!!.size}")
                pageState = LoadState.SUCCESS
                list = mutableListOf<Any>().apply {
                    add(bannerRes.data!!)
                    addAll(stickyRes.data!!.onEach {
                        it.tags.add(0, TagBean("置顶"))
                    })
                    addAll(articleRes.data!!.datas)
                }
                refreshingState = false
            } else {
                refreshingState = false
            }
        }
    }

    fun onLoad() {
        viewModelScope.launch {
            loadState = true
            val articleRes = Api.get().getHomeArticleList(page + 1)
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