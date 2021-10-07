package com.tubagus.news.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val NEWS_API_BASE_URL = "https://newsapi.org"
private const val NEWS_API_KEY = "09734487ae034b1db7dd8b3df1fd2250"

class Api {

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor {
                    it.proceed(
                        it.request().newBuilder().apply {
                            addHeader("X-Api-Key", NEWS_API_KEY)
                        }.build()
                    )
                }
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