package com.grandstream.playandroid.entity

import com.google.gson.annotations.SerializedName

data class AuthorBean (
    @SerializedName("articleList") val articleList: List<Any?>,
    @SerializedName("author") val author: String,
    @SerializedName("children") val children: List<Any?>,
    @SerializedName("courseId") val courseId: Long,
    @SerializedName("cover") val cover: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("id") val id: Long,
    @SerializedName("lisense") val lisense: String,
    @SerializedName("lisenseLink") val lisenseLink: String,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Long,
    @SerializedName("parentChapterId") val parentChapterId: Long,
    @SerializedName("type") val type: Long,
    @SerializedName("userControlSetTop") val userControlSetTop: Boolean,
    @SerializedName("visible") val visible: Long
)