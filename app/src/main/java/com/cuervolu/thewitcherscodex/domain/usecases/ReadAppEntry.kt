package com.cuervolu.thewitcherscodex.domain.usecases

import com.cuervolu.thewitcherscodex.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow


class ReadAppEntry(private val localUserManager: LocalUserManager) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}