package com.newsapp.domain.usecase

import com.newsapp.data.model.Article
import com.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class AddArticleFromDBUseCase @Inject constructor(
    private val repository: NewsRepository
){
    suspend operator fun invoke(article: Article) = repository.saveNewFromBookmarks(article)
}