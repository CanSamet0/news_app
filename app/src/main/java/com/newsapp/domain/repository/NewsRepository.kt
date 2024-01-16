package com.newsapp.domain.repository

import com.newsapp.data.Resource
import com.newsapp.data.model.Article
import com.newsapp.data.model.NewsRootResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines(): Resource<NewsRootResponse>

    suspend fun getSearchedNews(searchQuery: String): Resource<NewsRootResponse>

    suspend fun isBookmarked(articleUrl: String): Boolean

    suspend fun getSavedNews(): List<Article>

    suspend fun deleteNewsFromBookmarks(article: Article) : Boolean

    suspend fun saveNewFromBookmarks(article: Article)

}