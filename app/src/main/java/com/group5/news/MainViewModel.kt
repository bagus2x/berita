package com.group5.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group5.news.data.ArticlesResponse
import com.group5.news.network.Api
import com.group5.news.data.Result
import kotlinx.coroutines.launch

typealias ArticlesLiveData = MutableLiveData<Result<ArticlesResponse>>

class MainViewModel : ViewModel() {
    val headlinesTechnology: ArticlesLiveData = MutableLiveData()
    val headlinesGeneral: ArticlesLiveData = MutableLiveData()

    fun getTechnologyHeadlines(pageSize: Int) = viewModelScope.launch {
        getHeadlines(headlinesTechnology, "technology", "us", pageSize)
    }

    fun getGeneralHeadlines(pageSize: Int) = viewModelScope.launch {
        getHeadlines(headlinesGeneral, "general", "us", pageSize)
    }

    private suspend fun getHeadlines(data: ArticlesLiveData, category: String, country: String, pageSize: Int) {
        data.postValue(Result.Loading())
        try {
            val res = Api.news.getHeadlines(category, country)
            if (!res.isSuccessful) {
                headlinesGeneral.postValue(Result.Error(res.message() ?: "Error has occurred"))
                return
            }

            res.body()?.let {
                it.articles = it.articles.take(pageSize)
                data.postValue(Result.Success(it))
            }
        } catch (e: Exception) {
            data.postValue(Result.Error(e.message ?: "Error has occurred"))
        }
    }
}