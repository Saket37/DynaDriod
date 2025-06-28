package com.example.dynadroid.data

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.example.dynadroid.data.model.AppInfo
import com.example.dynadroid.domain.InstalledDeviceApps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private const val TAG = "InstalledDeviceApps"

class InstalledDeviceAppsImpl(private val context: Context) : InstalledDeviceApps {
    override fun getInstalledApps(): Flow<AppLoadResult> = flow {
        Log.d(TAG, "Starting to read installed apps...")

        // Reading the installed app using Intent, to bypass the goggle policy regarding the query all apps
        val packageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfos = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(0))
        } else {
            packageManager.queryIntentActivities(intent, 0)
        }
        val totalApps = resolveInfos.size

        val apps = resolveInfos.mapIndexed { index, resolveInfo ->
            val appName = resolveInfo.loadLabel(packageManager).toString()
            val appIcon = resolveInfo.loadIcon(packageManager)
            val packageName = resolveInfo.activityInfo.packageName

            val progressPercentage = ((index + 1).toFloat() / totalApps)
            emit(AppLoadResult.Progress(progressPercentage))

            AppInfo(
                appIcon = appIcon,
                appName = appName,
                packageName = packageName,
                appId = index + 1
            )
        }.sortedBy { it.appName }
        Log.d(TAG, "Successfully read ${apps} apps.")

        emit(AppLoadResult.Success(apps))
    }.flowOn(Dispatchers.IO)
}

sealed class AppLoadResult {
    data class Progress(val percent: Float) : AppLoadResult()
    data class Success(val apps: List<AppInfo>) : AppLoadResult()
}