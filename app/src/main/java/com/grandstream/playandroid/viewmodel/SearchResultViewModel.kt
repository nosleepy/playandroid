package com.grandstream.playandroid.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.launch

class SearchResultViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var list by mutableStateOf(listOf<ArticleBean>())
    var refreshingState by mutableStateOf(false)
    var loadState by mutableStateOf(false)
    var showLoading by mutableStateOf(false)
    var querykey = ""
    var page = 0

    fun query(keyword: String) {
        if (querykey.isEmpty()) {
            querykey = keyword
            firstLoad()
        }
    }

    fun firstLoad() {
        viewModelScope.launch {
            page = 0
            pageState = LoadState.LOADING
            val res = Api.get().search(page, querykey)
            if (res.isSuccess()) {
                pageState = LoadState.SUCCESS
                if (res.data!!.datas.isNotEmpty()) {
                    list = res.data!!.datas
                } else {
                    pageState = LoadState.EMPTY
                }
            } else {
                pageState = LoadState.FAIL
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            page = 0
            refreshingState = true
            val res = Api.get().search(page, querykey)
            if (res.isSuccess()) {
                if (res.data!!.datas.isNotEmpty()) {
                    list = res.data!!.datas
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
            val res = Api.get().search(page + 1, querykey)
//            Log.d("wlzhou", "page = ${page + 1}, res = $res")
            if (res.isSuccess() && res.data!!.datas.isNotEmpty()) {
                page++
                list = list.toMutableList().apply {
                    addAll(res.data!!.datas)
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