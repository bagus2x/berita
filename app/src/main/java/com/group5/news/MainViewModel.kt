package com.group5.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group5.news.data.NewsResponse
import com.group5.news.network.Api
import com.group5.news.utlities.StateResult
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val headlinesTechnology: MutableLiveData<StateResult<NewsResponse>> = MutableLiveData()
    val headlinesGeneral: MutableLiveData<StateResult<NewsResponse>> = MutableLiveData()

    fun getGeneralHeadlines(pageSize: Int) = viewModelScope.launch {
        headlinesGeneral.postValue(StateResult.Loading())
        try {
            val res = Api.news.getHeadlines("general", "us")
            if (res.isSuccessful) {
                res.body()?.let {
                    it.articles = it.articles.take(pageSize)
                    headlinesGeneral.postValue(StateResult.Success(it))
                }
                return@launch
            }

            headlinesGeneral.postValue(StateResult.Error(res.message() ?: "Error has occurred"))
        }catch (e: Exception) {
            headlinesTechnology.postValue(StateResult.Error(e.message.toString()))
        }
    }

    fun getTechnologyHeadlines(pageSize: Int) = viewModelScope.launch {
        headlinesTechnology.postValue(StateResult.Loading())
        try {
            val res = Api.news.getHeadlines("technology", "us")
            if (res.isSuccessful) {
                res.body()?.let {
                    it.articles = it.articles.take(pageSize)
                    headlinesTechnology.postValue(StateResult.Success(it))
                }
                return@launch
            }

            headlinesTechnology.postValue(StateResult.Error(res.message() ?: "Error has occurred"))
        } catch (e: Exception) {
            headlinesTechnology.postValue(StateResult.Error(e.message.toString()))
        }
    }
}