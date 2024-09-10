package com.dedesaepulloh.clone.newsapp.domain.usecases.news

import com.dedesaepulloh.clone.newsapp.data.local.NewsDao
import com.dedesaepulloh.clone.newsapp.domain.model.Article
import com.dedesaepulloh.clone.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }

}