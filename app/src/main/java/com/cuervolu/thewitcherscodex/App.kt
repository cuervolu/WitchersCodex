package com.cuervolu.thewitcherscodex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeTimber()
//        MusicPlayerManager.initialize(this)
//        MusicPlayerManager.startPlaying(this)
//
//        // Inicializa FirebaseApp
//        FirebaseApp.initializeApp(this)
//
//        // Configura el proveedor de App Check (Play Integrity)
//        val firebaseAppCheck = FirebaseAppCheck.getInstance()
//        firebaseAppCheck.installAppCheckProviderFactory(
//            PlayIntegrityAppCheckProviderFactory.getInstance()
//        )
    }

    private fun initializeTimber() {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber initialized")
        }
    }

}