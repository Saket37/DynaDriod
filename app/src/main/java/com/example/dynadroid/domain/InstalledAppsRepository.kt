package com.example.dynadroid.domain

import com.example.dynadroid.data.local.entity.SelectedAppInfo
import com.example.dynadroid.utils.AppLoadResult
import kotlinx.coroutines.flow.Flow

interface InstalledAppsRepository {
    suspend fun updateApps(packages: List<SelectedAppInfo>)

    suspend fun getSyncedApps(): Flow<AppLoadResult<List<SelectedAppInfo>>>
}