package com.example.dynadroid.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = White,
    primaryContainer = PrimaryBlueVariant,
    onPrimaryContainer = White,
    background = DarkBackground,
    onBackground = OnBackgroundText,
    surface = DarkBackground,
    onSurface = OnBackgroundText,
    surfaceVariant = DarkBackground,
    onSurfaceVariant = SecondaryText,
)

//private val LightColorScheme = lightColorScheme(
//    primary = PrimaryBlue,
//    onPrimary = White,
//    primaryContainer = PrimaryBlueVariant,
//    onPrimaryContainer = White,
//    background = Color.White,
//    onBackground = Color.Black,
//    surface = Color.White,
//    onSurface = Color.Black,
//    surfaceVariant = Color.LightGray,
//    onSurfaceVariant = Color.DarkGray,
//)

@Composable
fun DynaDroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}