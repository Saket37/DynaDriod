package com.example.dynadroid.domain

import com.example.dynadroid.data.model.AppInfo
import com.example.dynadroid.utils.AppLoadResult
import kotlinx.coroutines.flow.Flow

interface InstalledDeviceApps {
    fun getInstalledApps(): Flow<AppLoadResult<List<AppInfo>>>
}