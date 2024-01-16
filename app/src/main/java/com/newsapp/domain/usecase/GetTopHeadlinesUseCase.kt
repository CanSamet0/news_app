package com.newsapp.domain.usecase

import com.newsapp.data.Resource
import com.newsapp.data.model.NewsRootResponse
import com.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): Resource<NewsRootResponse> = newsRepository.getTopHeadlines()
}

