package com.gunt.kakaosearchrevision

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KaSearchApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: KaSearchApplication? = null
        fun ApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}