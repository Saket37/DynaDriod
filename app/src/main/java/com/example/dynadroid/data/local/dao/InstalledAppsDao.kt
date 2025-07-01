package com.example.dynadroid.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
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

    @Query("UPDATE installed_apps SET isChecked = :isChecked WHERE packageName = :packageName")
    suspend fun updateCheckedState(packageName: String, isChecked: Boolean)

    @Transaction
    suspend fun syncApps(installApps: List<SelectedAppInfo>, uninstallApps: List<String>) {
        deleteAppsByPackageName(uninstallApps)
        upsertApps(installApps)
    }

    @Update
    suspend fun updateApps(apps: List<SelectedAppInfo>)
}