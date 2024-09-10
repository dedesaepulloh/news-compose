package com.dedesaepulloh.clone.newsapp.domain.usecases.news

import com.dedesaepulloh.clone.newsapp.data.local.NewsDao
import com.dedesaepulloh.clone.newsapp.domain.model.Article
import com.dedesaepulloh.clone.newsapp.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }

}