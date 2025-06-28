package com.example.dynadroid.domain

import com.example.dynadroid.data.AppLoadResult
import com.example.dynadroid.data.model.AppInfo
import kotlinx.coroutines.flow.Flow

interface InstalledDeviceApps {
    fun getInstalledApps(): Flow<AppLoadResult>
}