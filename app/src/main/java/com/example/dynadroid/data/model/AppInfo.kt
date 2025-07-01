package com.example.dynadroid.data.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

data class AppInfo(
    val appName: String,
    val appIcon: Bitmap?,
    val packageName: String,
    val isChecked: Boolean = false

)
