package com.group5.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group5.news.data.NewsResponse
import com.group5.news.network.Api
import com.group5.news.data.Result
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val headlinesTechnology: MutableLiveData<Result<NewsResponse>> = MutableLiveData()
    val headlinesGeneral: MutableLiveData<Result<NewsResponse>> = MutableLiveData()

    fun getGeneralHeadlines(pageSize: Int) = viewModelScope.launch {
        headlinesGeneral.postValue(Result.Loading())
        try {
            val res = Api.news.getHeadlines("general", "us")
            if (res.isSuccessful) {
                res.body()?.let {
                    it.articles = it.articles.take(pageSize)
                    headlinesGeneral.postValue(Result.Success(it))
                }
                return@launch
            }

            headlinesGeneral.postValue(Result.Error(res.message() ?: "Error has occurred"))
        } catch (e: Exception) {
            headlinesTechnology.postValue(Result.Error(e.message ?: "Error has occurred"))
        }
    }

    fun getTechnologyHeadlines(pageSize: Int) = viewModelScope.launch {
        headlinesTechnology.postValue(Result.Loading())
        try {
            val res = Api.news.getHeadlines("technology", "us")
            if (res.isSuccessful) {
                res.body()?.let {
                    it.articles = it.articles.take(pageSize)
                    headlinesTechnology.postValue(Result.Success(it))
                }
                return@launch
            }

            headlinesGeneral.postValue(Result.Error(res.message() ?: "Error has occurred"))
        } catch (e: Exception) {
            headlinesTechnology.postValue(Result.Error(e.message ?: "Error has occurred"))
        }
    }
}