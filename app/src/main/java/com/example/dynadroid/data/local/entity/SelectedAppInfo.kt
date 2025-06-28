package com.example.dynadroid.data.local.entity

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "installed_apps")
data class SelectedAppInfo(
    @PrimaryKey(autoGenerate = true)
    val appId: String,
    val appName: String,
    val packageName: String,
    val appIcon: Drawable,
    val isChecked: Boolean = false
)
