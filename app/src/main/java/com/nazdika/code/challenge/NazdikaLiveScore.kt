package com.nazdika.code.challenge

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NazdikaLiveScore : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}