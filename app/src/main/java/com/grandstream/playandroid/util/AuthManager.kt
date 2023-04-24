package com.grandstream.playandroid.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.protobuf.InvalidProtocolBufferException
import com.grandstream.playandroid.PlayApplication
import com.grandstream.playandroid.api.Api
import com.grandstream.playandroid.entity.UserDataBean
import com.grandstream.playandroid.entity.UserInfo
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date

object UserInfoSerializer : Serializer<UserInfo> {
    override val defaultValue: UserInfo = UserInfo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserInfo {
        try {
            return UserInfo.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserInfo, output: OutputStream) = t.writeTo(output)
}

object AuthManager {
    private val scope = MainScope()
    private val userInternal = MutableLiveData<UserDataBean>()
    val user: LiveData<UserDataBean> = userInternal
    private val loginTimeInternal = MutableLiveData<String>()
    val loginTime: LiveData<String> = loginTimeInternal

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PlayApplication.context.packageName)
    private val Context.userInfoDataStore: DataStore<UserInfo> by dataStore(
        fileName = "user_info.pb",
        serializer = UserInfoSerializer
    )

    private val KEY_COUNT = intPreferencesKey("count")

    @SuppressLint("SimpleDateFormat")
    fun init() {
        scope.launch {
            PlayApplication.context.dataStore.edit { data -> data[KEY_COUNT] = data[KEY_COUNT]?.plus(1) ?: 1 }
            val count = PlayApplication.context.dataStore.data.first()[KEY_COUNT]
            val username = PlayApplication.context.userInfoDataStore.data.first().username
            val loginTime = PlayApplication.context.userInfoDataStore.data.first().loginTime
            Log.d("wlzhou", "username = $username, loginTime = $loginTime, count = $count")
            if (username.isNotEmpty()) {
                updateUserInfo()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun onLogin(username: String) {
        scope.launch {
            PlayApplication.context.userInfoDataStore.updateData { userInfo ->
                userInfo.toBuilder()
                    .setUsername(username)
                    .setLoginTime(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()))
                    .build()
            }
        }
        updateUserInfo()
    }

    private fun updateUserInfo() {
        scope.launch {
            val userRes = Api.get().getUserInfo()
            Log.d("wlzhou", "userRes = $userRes")
            if (userRes.isSuccess()) {
                userInternal.postValue(userRes.data)
            }
            loginTimeInternal.postValue(PlayApplication.context.userInfoDataStore.data.first().loginTime)
        }
    }

    fun onLogout() {
        scope.launch {
            userInternal.postValue(null)
            loginTimeInternal.postValue("")
            PlayApplication.context.userInfoDataStore.updateData { userInfo -> userInfo.toBuilder().clear().build() }
        }
    }

    fun isLogin(): Boolean {
        return userInternal.value != null
    }
}