package com.group5.news.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class Connectivity {
    private lateinit var connectivityManager: ConnectivityManager

    companion object {
        val instance = Connectivity()
    }

    fun initializeWithApplicationContext(context: Context) {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun isOnline(): Boolean {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
            when {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                else -> false
            }
        }
        return false
    }
}