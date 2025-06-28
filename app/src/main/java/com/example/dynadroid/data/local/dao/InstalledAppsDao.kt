package com.example.dynadroid.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.dynadroid.data.local.entity.SelectedAppInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface InstalledAppsDao {

    @Query("SELECT * from installed_apps")
    fun getAllApps(): Flow<List<SelectedAppInfo>>

    @Upsert
    suspend fun upsertApps(apps: List<SelectedAppInfo>)

    @Query("DELETE FROM installed_apps WHERE packageName IN (:packageName)")
    suspend fun deleteAppsByPackageName(packageName: List<String>)

    @Query("UPDATE installed_apps SET isChecked = :isChecked WHERE appId = :appId")
    suspend fun updateCheckedState(appId: String, isChecked: Boolean)
}