package com.example.dynadroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dynadroid.R
import com.example.dynadroid.ui.theme.NotoSans400Fs16
import com.example.dynadroid.utils.VerticalGap

@Composable
fun DynaDroidAppBar(
    modifier: Modifier = Modifier,
    leftBtnClick: () -> Unit,
    rightBtnClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(
                WindowInsets.navigationBars
            )
    ) {
        VerticalGap(12.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = { leftBtnClick() },
                modifier = Modifier.defaultMinSize(1.dp, 1.dp)
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    style = NotoSans400Fs16,
                    color = Color.White
                )
            }
            TextButton(
                onClick = { rightBtnClick() },
                modifier = Modifier.defaultMinSize(1.dp, 1.dp)
            ) {
                Text(
                    text = stringResource(R.string.next),
                    style = NotoSans400Fs16,
                    color = Color.White
                )
            }

        }
        VerticalGap(12.dp)
        content()
    }
}

@Preview
@Composable
private fun DynaDroidAppBarPreview() {
    DynaDroidAppBar(
        leftBtnClick = {},
        rightBtnClick = {},
        content = {})
}