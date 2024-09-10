package com.dedesaepulloh.clone.newsapp.presentation.bookmark

import com.dedesaepulloh.clone.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
)
