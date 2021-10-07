package com.group5.news

import android.app.Application
import com.group5.news.utilities.Connectivity

@Suppress("unused")
class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Connectivity.instance.initializeWithApplicationContext(this)
    }
}