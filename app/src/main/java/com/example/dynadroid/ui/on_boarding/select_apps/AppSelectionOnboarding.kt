package com.example.dynadroid.ui.on_boarding.select_apps

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dynadroid.R
import com.example.dynadroid.system_design.GradientBackground
import com.example.dynadroid.ui.installed_apps_list.AppListState
import com.example.dynadroid.ui.installed_apps_list.InstalledAppsListViewModel
import com.example.dynadroid.ui.theme.CardBackground
import com.example.dynadroid.ui.theme.NotoSans400Fs16
import com.example.dynadroid.ui.theme.NotoSans400Fs18
import com.example.dynadroid.ui.theme.PrimaryBlue
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs18
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs22
import com.example.dynadroid.ui.theme.TextColorDark
import com.example.dynadroid.utils.VerticalGap
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppSelectionScreenRoot(
    modifier: Modifier = Modifier, viewModel: InstalledAppsListViewModel = koinViewModel()
) {
    val listState = rememberLazyListState()
    GradientBackground(contentAlignment = Alignment.TopStart) {
        AppSelectionScreen(uiState = viewModel.uiState, listState = listState) {
            viewModel.loadApps()
        }
    }
}

@Composable
fun AppSelectionScreen(
    modifier: Modifier = Modifier,
    uiState: AppListState,
    listState: LazyListState,
    onSelectAppClick: () -> Unit
) {
    val cardShape = RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp, bottomStart = 80.dp)

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
                .clip(cardShape)
                .background(CardBackground)

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
                    //.weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .clickable { onSelectAppClick() }
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically) {
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
//                Button(
//                    onClick = {
//
//                    },
//                    modifier = Modifier
//                        .padding(horizontal = 30.dp)
//                        .fillMaxWidth(),
//                    shape = RoundedCornerShape(16.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = PrimaryBlue, contentColor = Color.White
//                    ),
//                    contentPadding = PaddingValues(vertical = 16.dp)
//                ) {
//                    Text(
//                        text = stringResource(id = R.string.next), style = SpaceGrotesk700Fs18
//                    )
//                }
                VerticalGap(8.dp)
                if (uiState.isLoading == false && uiState.apps.isEmpty().not()) {
                    LazyColumn(
                        state = listState, verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(uiState.apps) { index, app ->
                            AppsItem(appIcon = app.appIcon, appName = app.appName)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppsItem(modifier: Modifier = Modifier, appIcon: Drawable, appName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = appIcon, modifier = Modifier.size(32.dp), contentDescription = null
        )
        Text(text = appName, style = SpaceGrotesk700Fs18, color = TextColorDark)
    }

}


//@Preview
//@Composable
//private fun AppSelectionScreenPreview() {
//    AppSelectionScreen(uiState = AppListState())
//}