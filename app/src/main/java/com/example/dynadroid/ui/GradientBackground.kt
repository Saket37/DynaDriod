package com.example.dynadroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import com.example.dynadroid.ui.theme.DynaDroidTheme
import com.example.dynadroid.ui.theme.OffWhite
import com.example.dynadroid.utils.BackgroundPreview
import com.example.dynadroid.utils.dpToPx

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
    content: @Composable ColumnScope.() -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF232830), Color(0xFF141A1F)
                    )
                )
            )
            .drawBehind {
                val recTSize = Size(
                    width = 176.dpToPx(context),
                    height = 38.dpToPx(context)
                )
                val firstRect = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(
                                    x = (-43).dpToPx(context),
                                    y = (-9).dpToPx(context)
                                ),
                                size = recTSize
                            ),
                            cornerRadius = CornerRadius(35.dpToPx(context), 35.dpToPx(context))
                        )
                    )
                }
                val secondRect = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(
                                    x = size.width - 127.dpToPx(context),
                                    y = 120.dpToPx(context)
                                ),
                                size = recTSize
                            ),
                            cornerRadius = CornerRadius(35.dpToPx(context), 35.dpToPx(context))
                        )
                    )
                }

                val thirdRect = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(
                                    x = (-73).dpToPx(context),
                                    y = size.height - 134.dpToPx(context)
                                ),
                                size = recTSize
                            ),
                            cornerRadius = CornerRadius(35.dpToPx(context), 35.dpToPx(context))
                        )
                    )
                }

                val fourthRect = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(
                                    x = size.width - 122.dpToPx(context),
                                    y = size.height - 68.dpToPx(context)
                                ),
                                size = recTSize
                            ),
                            cornerRadius = CornerRadius(35.dpToPx(context), 35.dpToPx(context))
                        )
                    )
                }
                drawPath(fourthRect, OffWhite)
                drawPath(thirdRect, OffWhite)
                drawPath(secondRect, OffWhite)
                drawPath(firstRect, OffWhite)
            },
    ) {

    }
}

@BackgroundPreview
@Composable
private fun WelcomePreview() {
    DynaDroidTheme {
        GradientBackground() {}
    }
}