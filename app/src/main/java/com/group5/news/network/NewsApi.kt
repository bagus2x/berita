package com.group5.news.network

import com.group5.news.data.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("/NewsAPI/top-headlines/category/{category}/{country}.json")
    suspend fun getHeadlines(
        @Path("category")
        pageSize: String,
        @Path("country")
        country: String
    ): Response<ArticlesResponse>
}