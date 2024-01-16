package com.newsapp.presentation.detail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.data.model.Article
import com.newsapp.domain.usecase.AddArticleFromDBUseCase
import com.newsapp.domain.usecase.DeleteArticleFromDBUseCase
import com.newsapp.domain.usecase.IsBookmarkedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val isBookmarkedUseCase: IsBookmarkedUseCase,
    private val addBookmarkUseCase: AddArticleFromDBUseCase,
    private val deleteArticleFromDBUseCase: DeleteArticleFromDBUseCase,

) : ViewModel() {

    private lateinit var context: Context
    private val _isBookmarkedLiveData = MutableLiveData(false)
    val isBookmarkedLiveData = _isBookmarkedLiveData

    fun setContext(context: Context) {
        this.context = context
    }
    fun checkBookmarked(articleUrl: String) {
        viewModelScope.launch {
            _isBookmarkedLiveData.postValue(isBookmarkedUseCase.invoke(articleUrl))
        }
    }

    fun setBookmarked(article: Article) {
        viewModelScope.launch {
            if (_isBookmarkedLiveData.value == true) {
                deleteArticleFromDBUseCase.invoke(article)
                _isBookmarkedLiveData.postValue(false)
                showToast("Haber Favorilerden Çıkarıldı")
            } else {
                addBookmarkUseCase.invoke(article)
                _isBookmarkedLiveData.postValue(true)
                showToast("Haber Favorilere Eklendi")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}