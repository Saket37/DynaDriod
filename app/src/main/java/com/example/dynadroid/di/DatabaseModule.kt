package com.example.dynadroid.di

import androidx.room.Room
import com.example.dynadroid.data.local.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "dynadroid.db").build()
    }
    single {
        get<AppDatabase>().installedAppsDao
    }

}