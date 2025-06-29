package com.example.dynadroid.data

import androidx.core.graphics.drawable.toBitmap
import com.example.dynadroid.data.local.dao.InstalledAppsDao
import com.example.dynadroid.data.local.entity.SelectedAppInfo
import com.example.dynadroid.data.model.AppInfo
import com.example.dynadroid.domain.InstalledAppsRepository
import com.example.dynadroid.domain.InstalledDeviceApps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InstalledAppRepositoryImpl(
    private val installedAppsDao: InstalledAppsDao,
    private val installedDeviceApps: InstalledDeviceApps
) : InstalledAppsRepository {
    override suspend fun updateCheckedState(packageName: String, isChecked: Boolean) {
        //TODO Not yet implemented
    }

    private suspend fun syncWithDeviceApps(installedApps: List<AppInfo>) {
        val dbApps = installedAppsDao.getAllApps().first()
        val dbPackages = dbApps.map {
            it.packageName
        }.toSet()
        val installedPackages = installedApps.map { it.packageName }.toSet()

        val newApps = installedApps.asSequence()
            .filter { it.packageName !in dbPackages }
            .map {
                SelectedAppInfo(
                    appName = it.appName,
                    appIcon = it.appIcon.toBitmap(),
                    packageName = it.packageName,
                    isChecked = false,
                )
            }.toList()

        val uninstalledPackages = dbPackages - installedPackages

        installedAppsDao.syncApps(newApps, uninstalledPackages.toList())
    }

    override suspend fun getSyncedApps(): Flow<List<SelectedAppInfo>> = flow {
        installedDeviceApps.getInstalledApps().collect { appResult ->
            syncWithDeviceApps(appResult)
        }
        emitAll(installedAppsDao.getAllApps())
    }.flowOn(Dispatchers.IO)
}