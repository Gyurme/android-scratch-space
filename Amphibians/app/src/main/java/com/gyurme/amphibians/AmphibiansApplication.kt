package com.gyurme.amphibians

import android.app.Application
import com.gyurme.amphibians.data.AppContainer
import com.gyurme.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}