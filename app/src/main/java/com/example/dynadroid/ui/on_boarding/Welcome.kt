package com.example.dynadroid.ui.on_boarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dynadroid.R
import com.example.dynadroid.ui.GradientBackground
import com.example.dynadroid.ui.theme.DynaDroidTheme
import com.example.dynadroid.ui.theme.NotoSans400Fs18
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs32
import com.example.dynadroid.utils.VerticalGap



@Composable
fun Welcome(modifier: Modifier = Modifier) {
    GradientBackground(modifier = modifier) {
        // We are now directly inside the ColumnScope from GradientBackground.
        // We can use Arrangement and Alignment to center the content.
        Column(
            modifier = Modifier
                .fillMaxSize() // This column will fill the parent
                .padding(16.dp), // Add some padding from the edges
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.welcome_to_dynadriod),
                style = SpaceGrotesk700Fs32,
                // FIX 1: Use a color that is visible on a dark background
                color = MaterialTheme.colorScheme.onSurfaceVariant// Or MaterialTheme.colorScheme.inverseOnSurface
            )

            // Let's assume you have this composable for spacing
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.welcome_screen_details),
                style = NotoSans400Fs18,
                // FIX 1: Use a color that is visible on a dark background
                color = Color.White.copy(alpha = 0.8f) // A slightly dimmer white for secondary text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomePreview() {
    DynaDroidTheme {
        Welcome()
    }
}