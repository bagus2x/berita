package com.group5.news.data

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    class Error<T>(val message: String) : Result<T>()
    class Loading<T> : Result<T>()
}