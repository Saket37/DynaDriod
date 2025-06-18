package com.example.dynadroid.utils

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalGap(space: Dp) {
    Spacer(modifier = Modifier.height(space))
}

@Composable
fun HorizontalGap(space: Dp) {
    Spacer(modifier = Modifier.width(space))
}

fun Int.dpToPx(context: Context): Float = (this * context.resources.displayMetrics.density)