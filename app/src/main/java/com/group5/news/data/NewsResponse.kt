package com.group5.news.data

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)