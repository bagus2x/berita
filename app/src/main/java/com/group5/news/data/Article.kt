package com.group5.news.data

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val source: Source,
    val url: String,
    val title: String,
    val urlToImage: String,
    val publishedAt: String
)