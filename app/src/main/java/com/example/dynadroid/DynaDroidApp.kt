package com.example.dynadroid

import android.app.Application
import com.example.dynadroid.di.appModule
import com.example.dynadroid.di.databaseModule
import com.example.dynadroid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DynaDroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DynaDroidApp)
            modules(
                appModule,
                viewModelModule,
                databaseModule
            )
        }
    }
}