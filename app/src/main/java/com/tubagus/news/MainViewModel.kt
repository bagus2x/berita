package com.tubagus.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus.news.data.NewsResponse
import com.tubagus.news.network.Api
import com.tubagus.news.utlities.StateResult
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val headlinesGlobal: MutableLiveData<StateResult<NewsResponse>> = MutableLiveData()
    val headlinesLocal: MutableLiveData<StateResult<NewsResponse>> = MutableLiveData()

    fun getLocalHeadlines(pageSize: Int) = viewModelScope.launch {
        headlinesLocal.postValue(StateResult.Loading())
        val res = Api.news.getHeadlines("id", pageSize)
        if (res.isSuccessful) {
            res.body()?.let {
                headlinesLocal.postValue(StateResult.Success(it))
            }
            return@launch
        }

        headlinesLocal.postValue(StateResult.Error(res.message() ?: "Error has occurred"))
    }

    fun getGlobalHeadlines(pageSize: Int) = viewModelScope.launch {
        headlinesGlobal.postValue(StateResult.Loading())
        val res = Api.news.getHeadlines("us", pageSize)
        if (res.isSuccessful) {
            res.body()?.let {
                headlinesGlobal.postValue(StateResult.Success(it))
            }
            return@launch
        }

        headlinesGlobal.postValue(StateResult.Error(res.message() ?: "Error has occurred"))
    }
}