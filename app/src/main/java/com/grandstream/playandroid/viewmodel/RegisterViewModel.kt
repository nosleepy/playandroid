package com.grandstream.playandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.util.Toaster
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var showLoading by mutableStateOf(false)
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var repassword by mutableStateOf("")

    fun register(navController: NavController) {
        viewModelScope.launch {
            if (username.isEmpty()) {
                Toaster.show("用户名为空")
                return@launch
            }
            if (password.isEmpty()) {
                Toaster.show("密码为空")
                return@launch
            }
            if (repassword.isEmpty()) {
                Toaster.show("确认密码为空")
                return@launch
            }
            showLoading = true
            val registerRes = Api.get().register(username, password, repassword)
            showLoading = false
//            Log.d("wlzhou", "registerRes = $registerRes")
            if (registerRes.isSuccess()) {
                navController.popBackStack()
                Toaster.show("注册成功")
            } else {
                Toaster.show(registerRes.msg!!)
            }
        }
    }
}