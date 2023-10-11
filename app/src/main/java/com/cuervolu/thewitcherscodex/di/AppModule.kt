package com.cuervolu.thewitcherscodex.di

import android.app.Application
import com.cuervolu.thewitcherscodex.data.manager.LocalUserManagerImpl
import com.cuervolu.thewitcherscodex.domain.manager.LocalUserManager
import com.cuervolu.thewitcherscodex.domain.usecases.AppEntryUseCases
import com.cuervolu.thewitcherscodex.domain.usecases.ReadAppEntry
import com.cuervolu.thewitcherscodex.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun providesAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}