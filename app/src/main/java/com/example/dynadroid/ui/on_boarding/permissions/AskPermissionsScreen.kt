package com.example.dynadroid.ui.on_boarding.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.dynadroid.system_design.GradientBackground
import com.example.dynadroid.system_design.cardShape
import com.example.dynadroid.ui.components.DynaDroidAppBar
import com.example.dynadroid.ui.theme.CardBackground
import com.example.dynadroid.utils.BackgroundPreview

@Composable
fun AskPermissionsScreenRoot(modifier: Modifier = Modifier) {
    GradientBackground {
        AskPermissionsScreen()
    }
}

@Composable
fun AskPermissionsScreen(modifier: Modifier = Modifier) {

    DynaDroidAppBar(leftBtnClick = {}, rightBtnClick = {}) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(cardShape)
                .background(CardBackground)

        ) {

        }
    }

}

@BackgroundPreview
@Composable
private fun AskPermissionScreenPreview() {
    AskPermissionsScreenRoot()
}