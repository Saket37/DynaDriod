package com.example.dynadroid.ui.on_boarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.dynadroid.R
import com.example.dynadroid.ui.GradientBackground
import com.example.dynadroid.ui.theme.CardBackground
import com.example.dynadroid.ui.theme.DynaDroidTheme
import com.example.dynadroid.ui.theme.NotoSans400Fs18
import com.example.dynadroid.ui.theme.OffWhite
import com.example.dynadroid.ui.theme.PrimaryBlue
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs18
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs32
import com.example.dynadroid.ui.theme.TextColorDark
import com.example.dynadroid.utils.VerticalGap
import com.example.dynadroid.utils.dpToPx


@Composable
fun Welcome(
    onNextClick: () -> Unit
) {
    val context = LocalContext.current
    GradientBackground(
        modifier = Modifier.drawBehind {
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
        }

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 12.dp)
                .background(
                    CardBackground,
                    shape = RoundedCornerShape(
                        topStart = 100.dp,
                        topEnd = 100.dp,
                        bottomStart = 100.dp
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(vertical = 60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.welcome_to_dynadriod),
                    style = SpaceGrotesk700Fs32,
                    color = TextColorDark
                )
                VerticalGap(12.dp)
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.welcome_screen_details),
                    style = NotoSans400Fs18,
                    color = TextColorDark,
                    textAlign = TextAlign.Center
                )
                VerticalGap(36.dp)
                Button(
                    onClick = {
                        onNextClick()
                    },
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlue,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.get_started),
                        style = SpaceGrotesk700Fs18
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 50.dp)
                .size(232.dp)
                .background(color = PrimaryBlue, shape = CircleShape)

        )
    }
}

//@Composable
//fun WelcomeAdaptive() {
//    GradientBackground {
//        BoxWithConstraints {
//            val screenHeight = this.maxHeight
//            val screenWidth = this.maxWidth
//            val configuration = LocalConfiguration.current
//
//            Box(
//                Modifier
//                    .offset(x = -(screenWidth * 0.35f), y = -(screenHeight * 0.49f))
//                    .size(width = screenWidth * 0.45f, height = screenHeight * 0.05f)
//                    .clip(RoundedCornerShape(35.dp))
//                    .background(OffWhite),
//            )
//
//            Box(
//                Modifier
//                    .offset(x = (screenWidth * 0.40f), y = -(screenHeight * 0.35f))
//                    .size(width = screenWidth * 0.45f, height = screenHeight * 0.05f)
//                    .clip(RoundedCornerShape(35.dp))
//                    .background(OffWhite),
//            )
//
//            Box(
//                Modifier
//                    .offset(x = -(screenWidth * 0.35f), y = -(screenHeight * 0.49f))
//                    .size(width = screenWidth * 0.45f, height = screenHeight * 0.05f)
//                    .clip(RoundedCornerShape(35.dp))
//                    .background(OffWhite),
//            )
//
//        }
//    }
//}

@PreviewScreenSizes
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WelcomePreview() {
    DynaDroidTheme {
        Welcome(){}
    }
}