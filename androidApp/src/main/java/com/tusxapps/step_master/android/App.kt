package com.tusxapps.step_master.android

import android.app.Application
import androidx.compose.runtime.Immutable
import com.tusxapps.step_master.di.appModule
import com.tusxapps.step_master.utils.enableLogging
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule())
        }

        enableLogging()
    }
}