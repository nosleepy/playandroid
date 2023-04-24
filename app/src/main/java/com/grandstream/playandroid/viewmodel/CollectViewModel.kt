package com.grandstream.playandroid.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.ArticleBean
import com.grandstream.playandroid.entity.CollectBean
import com.grandstream.playandroid.util.Toaster
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.launch

class CollectViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var collectList by mutableStateOf(listOf<CollectBean>())
    var refreshingState by mutableStateOf(false)
    var loadState by mutableStateOf(false)
    var showLoading by mutableStateOf(false)
    var showDialog by mutableStateOf(false)
    var addMode by mutableStateOf(false)
    var editMode by mutableStateOf(false)
    private var page = 0

    var collectId by mutableStateOf(-1L)
    var title by mutableStateOf("")
    var anthor by mutableStateOf("")
    var link by mutableStateOf("")

    init {
        firstLoad()
    }

    private fun firstLoad() {
        viewModelScope.launch {
            page = 0
            pageState = LoadState.LOADING
            val collectListRes = Api.get().getCollectArticleList()
            if (collectListRes.isSuccess()) {
//                Log.d("wlzhou", "collectListRes = $collectListRes")
                collectList = collectListRes.data!!.datas
                pageState = LoadState.SUCCESS
            } else {
                pageState = LoadState.FAIL
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            page = 0
            refreshingState = true
            val collectListRes = Api.get().getCollectArticleList()
            if (collectListRes.isSuccess()) {
//                Log.d("wlzhou", "size = ${collectListRes.data!!.datas.size}")
                collectList = collectListRes.data!!.datas
                refreshingState = false
            } else {
                refreshingState = false
            }
        }
    }

    fun onLoad() {
        viewModelScope.launch {
            loadState = true
            val collectListRes = Api.get().getCollectArticleList(page + 1)
//            Log.d("wlzhou", "load page = ${page + 1}, collectListRes = $collectListRes")
            if (collectListRes.isSuccess() && collectListRes.data!!.datas.isNotEmpty()) {
                page++
                collectList = collectList.toMutableList().apply {
                    addAll(collectListRes.data!!.datas)
                }
                loadState = false
            } else {
                loadState = false
                Toaster.show("加载失败")
            }
        }
    }

    fun unCollect(collect: CollectBean) {
        viewModelScope.launch {
            showLoading = true
            Log.d("wlzhou", "collect = $collect")
            val res = if (collect.originId != -1L) {
                Api.get().unCollect(collect.originId)
            } else {
                Api.get().unPrivateCollect(collect.id, collect.originId)
            }
            Log.d("wlzhou", "res = $res")
            if (res.isSuccessIgnoreData()) {
                Toaster.show("取消收藏")
                onRefresh()
            }
            showLoading = false
        }
    }

    fun editCollect() {
        viewModelScope.launch {
            showLoading = true
            val res = Api.get().editPrivateArticle(collectId, title, anthor, link)
            if (res.isSuccessIgnoreData()) {
                Toaster.show("编辑成功")
                onRefresh()
            } else {
                Toaster.show("编辑失败")
            }
            showLoading = false
        }
    }

    fun addCollectArticle() {
        viewModelScope.launch {
            showLoading = true
            val res = Api.get().addCollectArticle(title, anthor, link)
//            Log.d("wlzhou", "res = $res")
            if (res.isSuccessIgnoreData()) {
                Toaster.show("添加成功")
                onRefresh()
            } else {
                Toaster.show("添加失败")
            }
            showLoading = false
        }
    }

    fun showInputInfo(collect: CollectBean) {
        title = collect.title
        anthor = collect.author
        link = collect.link
    }

    fun clearInputInfo() {
        title = ""
        anthor = ""
        link = ""
    }

    companion object {
        suspend fun collectOrCancel(article: ArticleBean): Boolean {
            val id = article.id
            if (article.collect) {
                val res = Api.get().unCollect(id)
                Log.d("wlzhou", "un res = $res")
                if (res.isSuccessIgnoreData()) {
                    Toaster.show("取消收藏")
                    article.collect = false
                    return true
                }
            } else {
                val res = Api.get().collect(id)
                Log.d("wlzhou", "res = $res")
                if (res.isSuccessIgnoreData()) {
                    Toaster.show("收藏成功")
                    article.collect = true
                    return true
                } else {
                    Toaster.show(res.msg!!)
                }
            }
            return false
        }
    }
}