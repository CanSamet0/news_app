package com.newsapp.data.remote

import com.newsapp.core.API_KEY
import com.newsapp.core.COUNTRY
import com.newsapp.core.PAGE_SIZE
import com.newsapp.data.model.NewsRootResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apikey: String = API_KEY,
        @Query("country") country: String = COUNTRY,
        @Query("pageSize") pageSize: Int = PAGE_SIZE
    ): Response<NewsRootResponse>

    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("apiKey") apikey: String = API_KEY,
        @Query("q") q: String,
        @Query("pageSize") pageSize: Int = PAGE_SIZE
    ): Response<NewsRootResponse>

}