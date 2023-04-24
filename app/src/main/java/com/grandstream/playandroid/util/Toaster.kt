package com.grandstream.playandroid.util

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.grandstream.playandroid.PlayApplication

object Toaster {
    fun show(content: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(PlayApplication.context, content, Toast.LENGTH_SHORT).show()
        }
    }
}