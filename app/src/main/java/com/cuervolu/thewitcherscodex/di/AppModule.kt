package com.cuervolu.thewitcherscodex.di

import android.app.Application
import com.cuervolu.thewitcherscodex.BuildConfig
import com.cuervolu.thewitcherscodex.data.manager.LocalUserManagerImpl
import com.cuervolu.thewitcherscodex.data.remote.TwitchApi
import com.cuervolu.thewitcherscodex.data.repository.FirestoreRepositoryImpl
import com.cuervolu.thewitcherscodex.data.repository.StreamsRepositoryImpl
import com.cuervolu.thewitcherscodex.domain.manager.LocalUserManager
import com.cuervolu.thewitcherscodex.domain.repository.FirestoreRepository
import com.cuervolu.thewitcherscodex.domain.repository.TwitchRepository
import com.cuervolu.thewitcherscodex.domain.usecases.app_entry.AppEntryUseCases
import com.cuervolu.thewitcherscodex.domain.usecases.app_entry.ReadAppEntry
import com.cuervolu.thewitcherscodex.domain.usecases.app_entry.SaveAppEntry
import com.cuervolu.thewitcherscodex.domain.usecases.characters.CharacterUseCases
import com.cuervolu.thewitcherscodex.domain.usecases.characters.GetCharacters
import com.cuervolu.thewitcherscodex.domain.usecases.streams.GetStreams
import com.cuervolu.thewitcherscodex.domain.usecases.streams.StreamUseCases
import com.cuervolu.thewitcherscodex.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
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

    @Provides
    @Singleton
    fun providesFirestoreRepository(): FirestoreRepository =
        FirestoreRepositoryImpl()

    @Provides
    @Singleton
    fun providesCharacterUseCases(firestoreRepository: FirestoreRepository): CharacterUseCases {
        return CharacterUseCases(getCharacters = GetCharacters(firestoreRepository))
    }

    @Provides
    @Singleton
    fun provideTwitchApi(): TwitchApi {
        val clientId = BuildConfig.CLIENT_ID
        val authorization = BuildConfig.Authorization
        Timber.w("clientId: $clientId, authorization: $authorization")
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val originalRequest: Request = chain.request()
                val requestBuilder: Request.Builder = originalRequest.newBuilder()
                    .header("Client-ID", clientId)
                    .header("Authorization", authorization)
                    .method(originalRequest.method, originalRequest.body)
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            })
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(TwitchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTwitchRepository(twitchApi: TwitchApi): TwitchRepository =
        StreamsRepositoryImpl(twitchApi)

    @Provides
    @Singleton
    fun provideStreamUseCases(twitchRepository: TwitchRepository): StreamUseCases {
        return StreamUseCases(
            getStreams = GetStreams(twitchRepository)
        )
    }
}