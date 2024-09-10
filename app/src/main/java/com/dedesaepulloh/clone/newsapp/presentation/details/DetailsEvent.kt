package com.dedesaepulloh.clone.newsapp.presentation.details

import com.dedesaepulloh.clone.newsapp.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article : Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}