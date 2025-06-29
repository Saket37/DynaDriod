package com.example.dynadroid.di

import com.example.dynadroid.data.InstalledAppRepositoryImpl
import com.example.dynadroid.data.InstalledDeviceAppsImpl
import com.example.dynadroid.domain.InstalledAppsRepository
import com.example.dynadroid.domain.InstalledDeviceApps
import org.koin.dsl.module

val appModule = module {

    single<InstalledDeviceApps> { InstalledDeviceAppsImpl(get()) }
    single<InstalledAppsRepository> { InstalledAppRepositoryImpl(get(), get()) }
}