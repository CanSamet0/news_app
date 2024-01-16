package com.newsapp.domain.usecase

import com.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class IsBookmarkedUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Boolean {
        return newsRepository.isBookmarked(url)
    }
}