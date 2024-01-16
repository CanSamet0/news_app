package com.newsapp.data.local.data_soruce

import com.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource{

    suspend fun saveNewFromBookmarks(article: Article)

    suspend fun getSavedNews(): List<Article>

    suspend fun deleteNewFromBookmarks(article: Article) : Boolean

    suspend fun isBookmarked(articleUrl: String): Boolean

}