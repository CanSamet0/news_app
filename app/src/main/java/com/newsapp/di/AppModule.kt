package com.newsapp.di

import android.app.Application
import androidx.room.Room
import com.newsapp.data.local.data_soruce.NewsLocalDataSource
import com.newsapp.data.local.data_soruce.NewsLocalDataSourceImpl
import com.newsapp.data.local.db.AppDatabase
import com.newsapp.data.local.db.ArticleDAO
import com.newsapp.data.remote.NewsApi
import com.newsapp.data.remote.data_source.NewsRemoteDataSource
import com.newsapp.data.remote.data_source.NewsRemoteDataSourceImpl
import com.newsapp.domain.repository.NewsRepository
import com.newsapp.domain.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(newsApi: NewsApi): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(articleDAO: ArticleDAO): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDAO)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        localDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(
            newsRemoteDataSource,
            localDataSource
        )
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "news_app_db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideArticleDAO(appDatabase: AppDatabase) = appDatabase.articleDAO()


}