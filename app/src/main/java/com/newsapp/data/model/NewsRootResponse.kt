package com.newsapp.data.model


import com.google.gson.annotations.SerializedName

data class NewsRootResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)