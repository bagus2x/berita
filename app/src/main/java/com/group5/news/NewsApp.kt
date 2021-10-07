package com.group5.news

import android.app.Application
import com.group5.news.utlities.Connectivity

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Connectivity.instance.initializeWithApplicationContext(this)
    }
}