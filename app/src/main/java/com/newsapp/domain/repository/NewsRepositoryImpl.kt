package com.newsapp.domain.repository

import com.newsapp.data.Resource
import com.newsapp.data.local.data_soruce.NewsLocalDataSource
import com.newsapp.data.model.Article
import com.newsapp.data.model.NewsRootResponse
import com.newsapp.data.remote.data_source.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) : NewsRepository {

    private fun responseToResource(response: Response<NewsRootResponse>) : Resource<NewsRootResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (resultResponse.articles.isNotEmpty()) {
                    return Resource.Success(resultResponse)
                }
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getTopHeadlines(): Resource<NewsRootResponse> {
        return responseToResource(remoteDataSource.getTopHeadlines())
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<NewsRootResponse> {
        return responseToResource(remoteDataSource.getSearchedNews(searchQuery))
    }

    override suspend fun isBookmarked(articleUrl: String): Boolean {
        return localDataSource.isBookmarked(articleUrl)
    }

    override suspend fun getSavedNews(): List<Article> {
        return localDataSource.getSavedNews()
    }

    override suspend fun deleteNewsFromBookmarks(article: Article): Boolean {
        return localDataSource.deleteNewFromBookmarks(article)
    }

    override suspend fun saveNewFromBookmarks(article: Article) {
        localDataSource.saveNewFromBookmarks(article)
    }

}