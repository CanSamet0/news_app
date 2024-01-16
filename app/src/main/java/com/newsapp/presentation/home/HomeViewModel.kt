package com.newsapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.data.Resource
import com.newsapp.data.model.NewsRootResponse
import com.newsapp.domain.usecase.GetSearchedNewsUseCase
import com.newsapp.domain.usecase.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
): ViewModel() {

    private val _newsLiveData: MutableLiveData<Resource<NewsRootResponse>> = MutableLiveData()
    val newsLiveData = _newsLiveData

    private val _searchQueryLiveData: MutableLiveData<String> = MutableLiveData()
    val searchQueryLiveData = _searchQueryLiveData

    fun getTopHeadlines() {
        viewModelScope.launch {
            _newsLiveData.postValue(Resource.Loading())
            val response: Resource<NewsRootResponse> = getTopHeadlinesUseCase.invoke()
            _newsLiveData.postValue(response)
        }
    }

    fun getSearchedNews(query: String) {
        viewModelScope.launch {
            _newsLiveData.postValue(Resource.Loading())
            val response: Resource<NewsRootResponse> = getSearchedNewsUseCase.invoke(query)
            _newsLiveData.postValue(response)
        }
    }

}