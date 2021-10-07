package com.tubagus.news.network

import com.tubagus.news.data.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        country: String,
        @Query("pageSize")
        pageSize: Int = 5
    ): Response<NewsResponse>
}