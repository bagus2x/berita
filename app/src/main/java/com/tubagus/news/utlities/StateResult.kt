package com.tubagus.news.utlities

sealed class StateResult<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : StateResult<T>(data)
    class Error<T>(message: String, data: T? = null) : StateResult<T>(data, message)
    class Loading<T> : StateResult<T>()
}