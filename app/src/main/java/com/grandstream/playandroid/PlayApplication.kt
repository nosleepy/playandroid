package com.grandstream.playandroid

import android.app.Application
import android.content.Context
import com.grandstream.playandroid.util.AuthManager

class PlayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        AuthManager.init()
    }

    companion object {
        lateinit var context: Context
    }
}