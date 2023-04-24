package com.grandstream.playandroid.entity

import com.google.gson.annotations.SerializedName

data class ToolBean (
    @SerializedName("desc") val desc: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Long,
    @SerializedName("isNew") val isNew: Long,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Long,
    @SerializedName("showInTab") val showInTab: Long,
    @SerializedName("tabName") val tabName: String,
    @SerializedName("visible") val visible: Long
)