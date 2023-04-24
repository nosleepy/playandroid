package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.ToolBean
import kotlinx.coroutines.launch

class ToolViewModel : ViewModel() {
    var list by mutableStateOf(listOf<ToolBean>())

    init {
        viewModelScope.launch {
            val res = Api.get().getToolList()
            if (res.isSuccess()) {
                list = res.data!!
            }
        }
    }
}