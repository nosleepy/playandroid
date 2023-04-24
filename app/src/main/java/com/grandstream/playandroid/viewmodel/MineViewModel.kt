package com.grandstream.playandroid.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.util.AuthManager
import kotlinx.coroutines.launch

class MineViewModel : ViewModel() {
    var user by mutableStateOf(AuthManager.user.value)
    var loginTime by mutableStateOf(AuthManager.loginTime.value)
    var showDialog by mutableStateOf(false)
    var showLoading by mutableStateOf(false)

    init {
        AuthManager.user.observeForever {
            Log.d("wlzhou", "MineViewModel -> update user")
            user = it
        }
        AuthManager.loginTime.observeForever {
            Log.d("wlzhou", "MineViewModel -> update time")
            loginTime = it
        }
    }

    fun logout() {
        viewModelScope.launch {
            showLoading = true
            val logoutRes = Api.get().logout()
            showLoading = false
            Log.d("wlzhou", "logoutRes = $logoutRes")
            AuthManager.onLogout()
        }
    }
}