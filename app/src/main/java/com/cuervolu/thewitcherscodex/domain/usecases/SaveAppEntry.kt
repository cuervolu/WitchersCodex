package com.cuervolu.thewitcherscodex.domain.usecases

import com.cuervolu.thewitcherscodex.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}