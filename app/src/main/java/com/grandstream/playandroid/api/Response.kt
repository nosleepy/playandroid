package com.grandstream.playandroid.api

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("errorCode") var code: Int = 0,
    @SerializedName("errorMsg") var msg: String? = "",
    @SerializedName("data") var data: T? = null,
) {
    fun isSuccess(): Boolean {
        return code == CODE_SUCCESS && data != null
    }

    fun isSuccessIgnoreData(): Boolean {
        return code == CODE_SUCCESS
    }

    companion object {
        const val CODE_SUCCESS = 0
    }
}