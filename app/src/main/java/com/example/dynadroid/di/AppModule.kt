package com.example.dynadroid.di

import com.example.dynadroid.data.InstalledDeviceAppsImpl
import com.example.dynadroid.domain.InstalledDeviceApps
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { androidContext() }

    single<InstalledDeviceApps> { InstalledDeviceAppsImpl(get()) }
}