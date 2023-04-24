package com.grandstream.playandroid.entity

import com.google.gson.annotations.SerializedName

data class CollectDataBean (
    @SerializedName("curPage") val curPage: Long,
    @SerializedName("datas") val datas: List<CollectBean>,
    @SerializedName("offset") val offset: Long,
    @SerializedName("over") val over: Boolean,
    @SerializedName("pageCount") val pageCount: Long,
    @SerializedName("size") val size: Long,
    @SerializedName("total") val total: Long
)

data class CollectBean (
    @SerializedName("author") val author: String,
    @SerializedName("chapterId") val chapterId: Long,
    @SerializedName("chapterName") val chapterName: String,
    @SerializedName("courseId") val courseId: Long,
    @SerializedName("desc") val desc: String,
    @SerializedName("envelopePic") val envelopePic: String,
    @SerializedName("id") val id: Long,
    @SerializedName("link") val link: String,
    @SerializedName("niceDate") val niceDate: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("originId") val originId: Long,
    @SerializedName("publishTime") val publishTime: Long,
    @SerializedName("title") val title: String,
    @SerializedName("userId") val userId: Long,
    @SerializedName("visible") val visible: Long,
    @SerializedName("zan") val zan: Long
)