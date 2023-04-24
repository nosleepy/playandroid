package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.CourseBean
import com.grandstream.playandroid.widget.LoadState
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {
    var pageState by mutableStateOf(LoadState.LOADING)
    var list by mutableStateOf(listOf<CourseBean>())

    init {
        viewModelScope.launch {
            pageState = LoadState.LOADING
            val res = Api.get().getCourseList()
            if (res.isSuccess()) {
                pageState = LoadState.SUCCESS
                list = res.data!!
            } else {
                pageState = LoadState.FAIL
            }
        }
    }
}