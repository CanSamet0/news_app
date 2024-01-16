package com.newsapp.data.local.data_soruce


import com.newsapp.data.local.db.ArticleDAO
import com.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
) : NewsLocalDataSource{

    override suspend fun saveNewFromBookmarks(article: Article) {
        articleDAO.insert(article)
    }

    override suspend fun getSavedNews(): List<Article> {
        return articleDAO.getAllSavedNews().reversed()
    }

    override suspend fun deleteNewFromBookmarks(article: Article) : Boolean{
        val affected = if (article.id != null) {
            articleDAO.deleteWithId(article.id)
        } else {
            article.url?.let { articleDAO.deleteWithUrl(it) }
        }
        return affected != null
    }

    override suspend fun isBookmarked(articleUrl: String): Boolean {
        return articleDAO.isBookmarked(articleUrl)
    }

}