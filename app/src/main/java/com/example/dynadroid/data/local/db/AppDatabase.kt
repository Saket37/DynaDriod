package com.example.dynadroid.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dynadroid.data.local.dao.InstalledAppsDao
import com.example.dynadroid.data.local.entity.SelectedAppInfo

@Database(entities = [SelectedAppInfo::class], version = 1)
@TypeConverters(BitmapConvertor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val installedAppsDao: InstalledAppsDao
}