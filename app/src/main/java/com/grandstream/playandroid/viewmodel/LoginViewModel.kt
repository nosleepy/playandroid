package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.util.AuthManager
import com.grandstream.playandroid.util.Toaster
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var showLoading by mutableStateOf(false)
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    fun login(navController: NavController) {
        viewModelScope.launch {
            if (username.isEmpty()) {
                Toaster.show("用户名为空")
                return@launch
            }
            if (password.isEmpty()) {
                Toaster.show("密码为空")
                return@launch
            }
            showLoading = true
            val loginRes = Api.get().login(username, password)
            showLoading = false
            if (loginRes.isSuccess()) {
                AuthManager.onLogin(loginRes.data!!.username)
                navController.popBackStack()
                Toaster.show("登录成功")
            } else {
                Toaster.show(loginRes.msg!!)
            }
        }
    }
}