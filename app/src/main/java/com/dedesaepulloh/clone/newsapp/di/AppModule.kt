package com.dedesaepulloh.clone.newsapp.di

import android.app.Application
import androidx.room.Room
import com.dedesaepulloh.clone.newsapp.data.local.NewsDao
import com.dedesaepulloh.clone.newsapp.data.local.NewsDatabase
import com.dedesaepulloh.clone.newsapp.data.local.NewsTypeConverter
import com.dedesaepulloh.clone.newsapp.data.manager.LocalUserManagerImpl
import com.dedesaepulloh.clone.newsapp.data.remote.NewsApi
import com.dedesaepulloh.clone.newsapp.data.repository.NewsRepositoryImpl
import com.dedesaepulloh.clone.newsapp.domain.manager.LocalUserManager
import com.dedesaepulloh.clone.newsapp.domain.repository.NewsRepository
import com.dedesaepulloh.clone.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.dedesaepulloh.clone.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.dedesaepulloh.clone.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.dedesaepulloh.clone.newsapp.domain.usecases.news.DeleteArticle
import com.dedesaepulloh.clone.newsapp.domain.usecases.news.GetNews
import com.dedesaepulloh.clone.newsapp.domain.usecases.news.NewsUseCases
import com.dedesaepulloh.clone.newsapp.domain.usecases.news.SearchNews
import com.dedesaepulloh.clone.newsapp.domain.usecases.news.SelectArticle
import com.dedesaepulloh.clone.newsapp.domain.usecases.news.SelectArticles
import com.dedesaepulloh.clone.newsapp.domain.usecases.news.UpsertArticle
import com.dedesaepulloh.clone.newsapp.utils.Constant.BASE_URL
import com.dedesaepulloh.clone.newsapp.utils.Constant.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application,
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager,
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao,
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application,
    ) : NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase,
    ) = newsDatabase.newsDao


}