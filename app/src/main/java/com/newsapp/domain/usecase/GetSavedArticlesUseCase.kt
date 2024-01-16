package com.newsapp.domain.usecase

import com.newsapp.data.model.Article
import com.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<Article> = repository.getSavedNews()
}
