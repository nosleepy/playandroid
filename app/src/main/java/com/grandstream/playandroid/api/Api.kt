package com.grandstream.playandroid.api

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.grandstream.playandroid.PlayApplication
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {
    private const val BASE_URL = "https://www.wanandroid.com/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
//            .cookieJar(object : CookieJar {
//                private val cookieStore = HashMap<String, List<Cookie>>()
//
//                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
//                    cookieStore[url.host()] = cookies
//                }
//
//                override fun loadForRequest(url: HttpUrl): List<Cookie> {
//                    val cookies = cookieStore[url.host()]
//                    return cookies ?: ArrayList<Cookie>()
//                }
//            })
            .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(PlayApplication.context))) // cookie持久化
            .build()
    }

    private val api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(IApi::class.java)
    }

    fun get(): IApi {
        return api
    }
}