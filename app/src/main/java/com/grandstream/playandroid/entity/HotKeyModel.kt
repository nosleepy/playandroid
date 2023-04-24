package com.grandstream.playandroid.entity

import com.google.gson.annotations.SerializedName

data class HotKeyBean (
    @SerializedName("id") val id: Long,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Long,
    @SerializedName("visible") val visible: Long
)