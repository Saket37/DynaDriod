package com.example.dynadroid.data.mappers

import com.example.dynadroid.data.local.entity.SelectedAppInfo
import com.example.dynadroid.data.model.AppInfo

fun SelectedAppInfo.toAppInfo(): AppInfo {
    return AppInfo(
        appName = appName,
        appIcon = appIcon,
        packageName = packageName,
        isChecked = isChecked
    )
}

fun AppInfo.toSelectedAppInfo(): SelectedAppInfo {
    return SelectedAppInfo(
        appIcon = appIcon!!,
        appName = appName,
        packageName = packageName,
        isChecked = isChecked
    )
}