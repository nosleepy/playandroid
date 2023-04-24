package com.grandstream.playandroid.viewmodel

import android.util.LongSparseArray
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.AuthorBean
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.launch

class WeChatViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var authorList by mutableStateOf(listOf<AuthorBean>())
    val tabViewModelMap = LongSparseArray<WeChatTabViewModel>()

    init {
        getAuthorList()
    }

    private fun getAuthorList() {
        viewModelScope.launch {
            pageState = LoadState.LOADING
            val authorListRes = Api.get().getWeChatAuthorList()
            if (authorListRes.isSuccess()) {
                authorList = authorListRes.data!!
                pageState = LoadState.SUCCESS
            } else {
                pageState = LoadState.FAIL
            }
        }
    }
}