package com.example.dynadroid.ui.on_boarding.select_apps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dynadroid.R
import com.example.dynadroid.system_design.GradientBackground
import com.example.dynadroid.ui.theme.CardBackground
import com.example.dynadroid.ui.theme.NotoSans400Fs16
import com.example.dynadroid.ui.theme.NotoSans400Fs18
import com.example.dynadroid.ui.theme.PrimaryBlue
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs18
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs22
import com.example.dynadroid.ui.theme.TextColorDark
import com.example.dynadroid.utils.VerticalGap

@Composable
fun AppSelectionScreen(modifier: Modifier = Modifier) {
    GradientBackground(contentAlignment = Alignment.TopStart) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            VerticalGap(12.dp)
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.app_name),
                style = SpaceGrotesk700Fs22,
                color = Color.White
            )
            VerticalGap(12.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        CardBackground,
                        RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp, bottomStart = 80.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(vertical = 20.dp)) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                        text = stringResource(id = R.string.select_apps),
                        style = SpaceGrotesk700Fs22,
                        color = TextColorDark
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = stringResource(id = R.string.select_apps_details),
                        style = NotoSans400Fs18,
                        color = TextColorDark
                    )
                    VerticalGap(24.dp)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .weight(1f)
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {}
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.apps_dynamic_island),
                                    style = SpaceGrotesk700Fs18,
                                    color = TextColorDark
                                )
                                VerticalGap(4.dp)
                                Text(
                                    text = stringResource(id = R.string.tap_to_choose_destination),
                                    style = NotoSans400Fs16,
                                    color = TextColorDark
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .size(35.dp)
                                    .background(color = PrimaryBlue, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null,
                                    Modifier.rotate(-90f),
                                    tint = Color.White
                                )
                            }
                        }
                    }
                    Button(
                        onClick = {},
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
                            text = stringResource(id = R.string.next),
                            style = SpaceGrotesk700Fs18
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
private fun AppSelectionScreenPreview() {
    AppSelectionScreen()
}