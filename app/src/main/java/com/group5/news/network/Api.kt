package com.group5.news.network

import com.group5.news.utilities.Connectivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private const val NEWS_API_BASE_URL = "https://saurav.tech"

class Api {

    companion object {
        private val retrofit by lazy {
            val client = OkHttpClient.Builder()
                .addInterceptor {
                    if (!Connectivity.instance.isOnline()) {
                        throw IOException("No internet connection")
                    } else {
                        it.proceed(it.request())
                    }
                }
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

            Retrofit.Builder()
                .baseUrl(NEWS_API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val news: NewsApi by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }
}