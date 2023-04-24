package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.SystemBean
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.launch

class SystemViewModel : ViewModel() {
    var list by mutableStateOf(listOf<SystemBean>())
    var pageState by mutableStateOf(LoadState.LOADING)

    init {
        viewModelScope.launch {
            pageState = LoadState.LOADING
            val res = Api.get().getSystemList()
            if (res.isSuccess()) {
                pageState = LoadState.SUCCESS
                list = res.data!!
            } else {
                pageState = LoadState.FAIL
            }
        }
    }
}