package com.dedesaepulloh.clone.newsapp.presentation.search

import androidx.paging.PagingData
import com.dedesaepulloh.clone.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
)