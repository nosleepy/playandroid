package com.grandstream.playandroid.entity

import com.google.gson.annotations.SerializedName

data class UserDataBean (
    @SerializedName("coinInfo") val coinInfo: CoinInfoBean,
    @SerializedName("collectArticleInfo") val collectArticleInfo: CollectArticleInfoBean,
    @SerializedName("userInfo") val userInfo: UserInfoBean,
)

data class UserInfoBean (
    @SerializedName("admin") val admin: Boolean,
    @SerializedName("chapterTops") val chapterTops: List<Any?>,
    @SerializedName("coinCount") val coinCount: Long,
    @SerializedName("collectIds") val collectIds: List<Any?>,
    @SerializedName("email") val email: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Long,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("password") val password: String,
    @SerializedName("publicName") val publicName: String,
    @SerializedName("token") val token: String,
    @SerializedName("type") val type: Long,
    @SerializedName("username") val username: String
)

data class CoinInfoBean (
    @SerializedName("coinCount") val coinCount: Long,
    @SerializedName("level") val level: Long,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("userID") val userID: Long,
    @SerializedName("username") val username: String
)

data class CollectArticleInfoBean (
    @SerializedName("count") val count: Long
)

data class ShareDataBean (
    @SerializedName("coinInfo") val coinInfo: CoinInfoBean,
    @SerializedName("shareArticles") val shareArticles: ArticleDataBean
)