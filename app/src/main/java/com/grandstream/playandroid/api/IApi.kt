package com.grandstream.playandroid.api

import com.grandstream.playandroid.entity.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IApi {
    @GET("banner/json")
    suspend fun getHomeBanner(): Response<List<BannerBean>>

    @GET("article/list/{page}/json")
    suspend fun getHomeArticleList(@Path("page") page: Int = 0): Response<ArticleDataBean>

    @GET("article/top/json")
    suspend fun getStickyArticle(): Response<List<ArticleBean>>

    @GET("user_article/list/{page}/json")
    suspend fun getSquareArticleList(@Path("page") page: Int = 0): Response<ArticleDataBean>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Response<UserInfoBean>

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String,
    ): Response<UserInfoBean>

    @GET("user/logout/json")
    suspend fun logout(): Response<String>

    @GET("user/lg/userinfo/json")
    suspend fun getUserInfo(): Response<UserDataBean>

    @GET("wxarticle/chapters/json")
    suspend fun getWeChatAuthorList(): Response<List<AuthorBean>>

    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getWeChatArticleList(
        @Path("id") id: Long,
        @Path("page") page: Int = 0,
    ): Response<ArticleDataBean>

    @GET("lg/collect/list/{page}/json")
    suspend fun getCollectArticleList(@Path("page") page: Int = 0): Response<CollectDataBean>

    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Long): Response<String>

    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Long): Response<String>

    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    suspend fun unPrivateCollect(@Path("id") id: Long, @Field("originId") originId: Long): Response<String>

    @FormUrlEncoded
    @POST("lg/user_article/add/json")
    suspend fun shareArticle(
        @Field("title") title: String,
        @Field("link") link: String,
    ): Response<String>

    @GET("user/lg/private_articles/{page}/json")
    suspend fun getPrivateArticleList(@Path("page") page: Int = 1): Response<ShareDataBean>

    @POST("lg/user_article/delete/{id}/json")
    suspend fun deletePrivateArticle(@Path("id") id: Long): Response<String>

    @FormUrlEncoded
    @POST("lg/collect/user_article/update/{id}/json")
    suspend fun editPrivateArticle(
        @Path("id") id: Long,
        @Field("title") title: String,
        @Field("author") author: String,
        @Field("link") link: String,
    ): Response<String>

    @FormUrlEncoded
    @POST("lg/collect/add/json")
    suspend fun addCollectArticle(
        @Field("title") title: String,
        @Field("author") author: String,
        @Field("link") link: String,
    ): Response<CollectBean>

    @GET("hotkey/json")
    suspend fun searchHotKey(): Response<List<HotKeyBean>>

    @FormUrlEncoded
    @POST("article/query/{page}/json")
    suspend fun search(
        @Path("page") page: Int = 0,
        @Field("k") keyword: String,
    ): Response<ArticleDataBean>

    @GET("chapter/547/sublist/json")
    suspend fun getCourseList(): Response<List<CourseBean>>

    @GET("article/list/{page}/json")
    suspend fun getChapterList(@Path("page") page: Int, @Query("cid") id: Long, @Query("order_type") type: Int = 1): Response<ArticleDataBean>

    @GET("tree/json")
    suspend fun getSystemList(): Response<List<SystemBean>>

    @GET("article/list/{page}/json")
    suspend fun getSystemArticleList(@Path("page") page: Int = 0, @Query("cid") id: Long): Response<ArticleDataBean>

    @GET("tools/list/json")
    suspend fun getToolList(): Response<List<ToolBean>>
}