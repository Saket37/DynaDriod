package com.example.dynadroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.dynadroid.ui.theme.DynaDroidTheme
import com.example.dynadroid.utils.BackgroundPreview

//private val largeRadialGradient = object : ShaderBrush() {
//    override fun createShader(size: Size): Shader {
//        val biggerDimension = maxOf(size.height, size.width)
//        return RadialGradientShader(
//            colors = listOf(Color(0xFF232830), Color(0xFF141A1F)),
//            center = size.center,
//            radius = biggerDimension / 2f,
//            colorStops = listOf(0f, 0.95f)
//        )
//    }
//
//}
@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF232830), Color(0xFF141A1F)
                    )
                )
            )
            .then(modifier),
        contentAlignment = contentAlignment
    ) {
        content()
    }
}

@BackgroundPreview
@Composable
private fun BackgroundPreview() {
    DynaDroidTheme {
        GradientBackground(Modifier, Alignment.Center) {}
    }
}