package com.grandstream.playandroid.entity

import com.google.gson.annotations.SerializedName

data class BannerBean (
    @SerializedName("desc") val desc: String,
    @SerializedName("id") val id: Long,
    @SerializedName("imagePath") val imagePath: String,
    @SerializedName("isVisible") val isVisible: Long,
    @SerializedName("order") val order: Long,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: Long,
    @SerializedName("url") val url: String
)
