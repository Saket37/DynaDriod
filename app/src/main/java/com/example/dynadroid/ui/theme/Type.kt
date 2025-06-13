package com.example.dynadroid.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dynadroid.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val SpaceGrotesk700Fs22 = TextStyle(
    fontSize = 22.sp,
    fontFamily = FontFamily(Font(R.font.space_grotesk)),
    fontWeight = FontWeight.Bold
)

val SpaceGrotesk700Fs32 = TextStyle(
    fontSize = 32.sp,
    fontFamily = FontFamily(Font(R.font.space_grotesk)),
    fontWeight = FontWeight.Bold
)

val SpaceGrotesk700Fs18 = TextStyle(
    fontSize = 18.sp,
    fontFamily = FontFamily(Font(R.font.space_grotesk)),
    fontWeight = FontWeight.Bold
)

val NotoSans400Fs18 = TextStyle(
    fontSize = 18.sp,
    fontFamily = FontFamily(Font(R.font.noto_sans_variable)),
    fontWeight = FontWeight.Normal
)
val NotoSans400Fs16 = TextStyle(
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(R.font.noto_sans_variable)),
    fontWeight = FontWeight.Normal
)
