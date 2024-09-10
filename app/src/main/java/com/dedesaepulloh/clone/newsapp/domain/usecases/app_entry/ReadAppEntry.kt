package com.dedesaepulloh.clone.newsapp.domain.usecases.app_entry

import com.dedesaepulloh.clone.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager,
) {
    operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}