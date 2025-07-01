package com.example.dynadroid.data

import com.example.dynadroid.data.local.dao.InstalledAppsDao
import com.example.dynadroid.data.local.entity.SelectedAppInfo
import com.example.dynadroid.data.model.AppInfo
import com.example.dynadroid.domain.InstalledAppsRepository
import com.example.dynadroid.domain.InstalledDeviceApps
import com.example.dynadroid.utils.AppLoadResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlin.time.Duration.Companion.milliseconds

class InstalledAppRepositoryImpl(
    private val installedAppsDao: InstalledAppsDao,
    private val installedDeviceApps: InstalledDeviceApps
) : InstalledAppsRepository {
    override suspend fun updateApps(packages: List<SelectedAppInfo>) {
        installedAppsDao.updateApps(packages)
    }

    private suspend fun syncWithDeviceApps(
        installedApps: List<AppInfo>,
    ): Flow<Float> = flow {
        val dbApps = installedAppsDao.getAllApps().first()
        val dbPackages = dbApps.map {
            it.packageName
        }.toSet()
        val installedPackages = installedApps.map { it.packageName }.toSet()
        emit(0.80f)

        val newApps = installedApps.asSequence()
            .filter { it.packageName !in dbPackages }
            .map {
                it.appIcon?.let { packageName ->
                    SelectedAppInfo(
                        appName = it.appName,
                        appIcon = packageName,
                        packageName = it.packageName,
                        isChecked = false,
                    )
                }
            }.toList()
        emit(0.90f)

        val uninstalledPackages = dbPackages - installedPackages
        emit(0.95f)
        installedAppsDao.syncApps(newApps.filterNotNull(), uninstalledPackages.toList())
        delay(200.milliseconds)
        emit(1.0f)
    }

    override suspend fun getSyncedApps(): Flow<AppLoadResult<List<SelectedAppInfo>>> = flow {
        installedDeviceApps.getInstalledApps().collect { appResult ->
            when (appResult) {
                is AppLoadResult.Progress -> emit(appResult)
                is AppLoadResult.Success -> {
                    syncWithDeviceApps(appResult.data).collect { progress ->
                        emit(AppLoadResult.Progress(progress))
                    }

                }
            }
        }

        emitAll(
            installedAppsDao.getAllApps()
                .map { apps -> AppLoadResult.Success(apps) }
        )
    }.flowOn(Dispatchers.IO)
}