package com.newsapp.data.remote.data_source

import com.newsapp.data.model.NewsRootResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(): Response<NewsRootResponse>

    suspend fun getSearchedNews(query: String): Response<NewsRootResponse>

}