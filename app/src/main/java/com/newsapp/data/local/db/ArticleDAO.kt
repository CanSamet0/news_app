package com.newsapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM articles")
    suspend fun getAllSavedNews(): List<Article>

    @Query("DELETE FROM articles WHERE url = :articleUrl")
    suspend fun deleteWithUrl(articleUrl: String): Int

    @Query("DELETE FROM articles WHERE id = :articleId")
    suspend fun deleteWithId(articleId: Int): Int

    @Query("SELECT EXISTS(SELECT * FROM articles WHERE url = :articleUrl)")
    suspend fun isBookmarked(articleUrl: String): Boolean

}