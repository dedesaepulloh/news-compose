package com.dedesaepulloh.clone.newsapp.data.remote.dto

import com.dedesaepulloh.clone.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
)