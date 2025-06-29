package com.example.dynadroid.data.local.entity

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "installed_apps")
data class SelectedAppInfo(
//    @PrimaryKey(autoGenerate = true)
//    val appId: Long,
    val appName: String,
    @PrimaryKey
    val packageName: String,
    val appIcon: Bitmap,
    val isChecked: Boolean = false
)
