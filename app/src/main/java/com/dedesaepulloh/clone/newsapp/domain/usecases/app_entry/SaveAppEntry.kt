package com.dedesaepulloh.clone.newsapp.domain.usecases.app_entry

import com.dedesaepulloh.clone.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}