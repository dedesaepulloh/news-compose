package com.dedesaepulloh.clone.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.dedesaepulloh.clone.newsapp.domain.model.Article
import com.dedesaepulloh.clone.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(
        searchQuery: String,
        sources: List<String>,
    ): Flow<PagingData<Article>> {
        return newsRepository.searchNews(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}