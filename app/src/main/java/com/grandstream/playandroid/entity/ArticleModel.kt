package com.grandstream.playandroid.entity

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import com.grandstream.playandroid.ui.theme.CustomColor

data class ArticleBean (
    @SerializedName("adminAdd") val adminAdd: Boolean,
    @SerializedName("apkLink") val apkLink: String,
    @SerializedName("audit") val audit: Long,
    @SerializedName("author") val author: String,
    @SerializedName("canEdit") val canEdit: Boolean,
    @SerializedName("chapterId") val chapterId: Long,
    @SerializedName("chapterName") val chapterName: String,
    @SerializedName("collect") var collect: Boolean,
    @SerializedName("courseId") val courseId: Long,
    @SerializedName("desc") val desc: String,
    @SerializedName("descMd") val descMd: String,
    @SerializedName("envelopePic") val envelopePic: String,
    @SerializedName("fresh") val fresh: Boolean,
    @SerializedName("host") val host: String,
    @SerializedName("id") val id: Long,
    @SerializedName("isAdminAdd") val isAdminAdd: Boolean,
    @SerializedName("link") val link: String,
    @SerializedName("niceDate") val niceDate: String,
    @SerializedName("niceShareDate") val niceShareDate: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("prefix") val prefix: String,
    @SerializedName("projectLink") val projectLink: String,
    @SerializedName("publishTime") val publishTime: Long,
    @SerializedName("realSuperChapterId") val realSuperChapterId: Long,
    @SerializedName("route") val route: Boolean,
    @SerializedName("selfVisible") val selfVisible: Long,
    @SerializedName("shareDate") val shareDate: Long,
    @SerializedName("shareUser") val shareUser: String,
    @SerializedName("superChapterId") val superChapterId: Long,
    @SerializedName("superChapterName") val superChapterName: String,
    @SerializedName("tags") val tags: MutableList<TagBean>,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: Long,
    @SerializedName("userId") val userId: Long,
    @SerializedName("visible") val visible: Long,
    @SerializedName("zan") val zan: Long
)

data class ArticleDataBean (
    @SerializedName("curPage") val curPage: Long,
    @SerializedName("datas") val datas: List<ArticleBean>,
    @SerializedName("offset") val offset: Long,
    @SerializedName("over") val over: Boolean,
    @SerializedName("pageCount") val pageCount: Long,
    @SerializedName("size") val size: Long,
    @SerializedName("total") val total: Long
)

data class TagBean (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String = ""
) {
    fun getColor(): Color {
        return when(name) {
            "置顶" -> CustomColor.cranesbill
            "本站发布" -> CustomColor.blue
            "问答" -> CustomColor.addicted
            "公众号" -> CustomColor.green
            "项目" -> CustomColor.fizz
            else -> Color.Gray
        }
    }
}