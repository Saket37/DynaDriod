package com.example.dynadroid.domain

import com.example.dynadroid.data.local.entity.SelectedAppInfo
import com.example.dynadroid.utils.AppLoadResult
import kotlinx.coroutines.flow.Flow

interface InstalledAppsRepository {
    suspend fun updateCheckedState(packageName: String, isChecked: Boolean)

    suspend fun getSyncedApps(): Flow<AppLoadResult<List<SelectedAppInfo>>>
}