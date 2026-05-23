package com.baha.sushigarden

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SushiGardenApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        try {
            FirebaseApp.initializeApp(this)
            Timber.i("Firebase initialized")
        } catch (e: Exception) {
            Timber.w(e, "Firebase not configured, using fake auth")
        }
    }
}
