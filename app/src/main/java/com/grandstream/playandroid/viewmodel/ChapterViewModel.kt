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

class ChapterViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var list by mutableStateOf(listOf<ArticleBean>())
    var page = 0
    var queryId = 0L

    fun query(id: Long) {
        if (queryId == 0L) {
            queryId = id
            viewModelScope.launch {
                pageState = LoadState.LOADING
                val total = Api.get().getChapterList(0, queryId).data!!.total
                while (list.size.toLong() != total) {
                    val res = Api.get().getChapterList(page++, queryId)
                    list = list.toMutableList().apply { addAll(res.data!!.datas) }
                }
                pageState = LoadState.SUCCESS
            }
        }
    }
}