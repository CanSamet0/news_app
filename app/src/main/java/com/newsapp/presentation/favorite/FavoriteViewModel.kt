package com.newsapp.presentation.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.data.model.Article
import com.newsapp.domain.usecase.GetSavedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase
): ViewModel() {

    private val _favoriteArticlesLiveData = MutableLiveData<List<Article>>()
    val favoriteArticlesLiveData = _favoriteArticlesLiveData


    fun getArticlesFromDb(){
        viewModelScope.launch {
            val list = getSavedArticlesUseCase.invoke()
            _favoriteArticlesLiveData.postValue(list)
        }
    }

}