package com.example.dynadroid

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DynaDroidApp: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DynaDroidApp)
        }
    }
}