package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.HotKeyBean
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    var keyword by mutableStateOf("")
    var history by mutableStateOf(listOf<String>())
    var hotKeys by mutableStateOf(listOf<HotKeyBean>())

    init {
        getHotKey()
    }

    private fun getHotKey() {
        viewModelScope.launch {
            val res = Api.get().searchHotKey()
            if (res.isSuccess()) {
                hotKeys = res.data!!
            }
        }
    }

    fun addHistory(item: String) {
        viewModelScope.launch {
            if (item.isEmpty() || history.contains(item)) {
                return@launch
            }
            history = history.toMutableList().apply { add(0, item) }
        }
    }

    fun removeHistory(item: String) {
        viewModelScope.launch {
            if (item.isEmpty() || !history.contains(item)) {
                return@launch
            }
            history = history.toMutableList().apply { remove(item) }
        }
    }
}