package com.newsapp.data.remote.data_source

import com.newsapp.data.model.NewsRootResponse
import com.newsapp.data.remote.NewsApi
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRemoteDataSource {

    override suspend fun getTopHeadlines(): Response<NewsRootResponse> {
        return newsApi.getTopHeadlines()
    }

    override suspend fun getSearchedNews(query: String): Response<NewsRootResponse> {
        return newsApi.getSearchedNews(q = query)
    }

}