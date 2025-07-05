@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.example.dynadroid.ui.on_boarding.select_apps

import android.graphics.Bitmap
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.dynadroid.R
import com.example.dynadroid.system_design.GradientBackground
import com.example.dynadroid.system_design.cardShape
import com.example.dynadroid.ui.components.DynaDroidAppBar
import com.example.dynadroid.ui.components.ScrollToTop
import com.example.dynadroid.ui.theme.CardBackground
import com.example.dynadroid.ui.theme.MidGrayBackground
import com.example.dynadroid.ui.theme.NotoSans400Fs16
import com.example.dynadroid.ui.theme.NotoSans400Fs18
import com.example.dynadroid.ui.theme.PrimaryBlue
import com.example.dynadroid.ui.theme.PrimaryBlueVariant
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs18
import com.example.dynadroid.ui.theme.SpaceGrotesk700Fs22
import com.example.dynadroid.ui.theme.TextColorDark
import com.example.dynadroid.ui.theme.White
import com.example.dynadroid.utils.VerticalGap
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppSelectionScreenRoot(
    modifier: Modifier = Modifier, viewModel: InstalledAppsListViewModel = koinViewModel(),
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    // TODO add jump to top
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    GradientBackground(contentAlignment = Alignment.TopStart) {
        AppSelectionScreen(
            uiState = uiState,
            listState = listState,
            onSkipClick = { onSkipClick() },
            onNextClick = {
                viewModel.saveChangesToDb()
                onNextClick()
            },
            onCheckChanged = { packageName, checkedValue ->
                viewModel.updateAppsState(packageName, checkedValue)
            }) {
            viewModel.searchApp()
        }
    }
}

private val JumpToTopThreshold = 56.dp

@Composable
fun AppSelectionScreen(
    modifier: Modifier = Modifier,
    uiState: AppListState,
    listState: LazyListState,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    onCheckChanged: (String, Boolean) -> Unit,
    onSelectAppClick: () -> Unit
) {
    val scope = rememberCoroutineScope()

    val animatedProgress by
    animateFloatAsState(
        targetValue = uiState.loadingProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    val scrollThreshold = with(LocalDensity.current) {
        JumpToTopThreshold.toPx()
    }
    val scrollToTopButtonEnabled by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0 ||
                    listState.firstVisibleItemScrollOffset > scrollThreshold
        }
    }
    DynaDroidAppBar(leftBtnClick = { onSkipClick() }, rightBtnClick = { onNextClick() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(cardShape)
                .background(CardBackground)

        ) {
            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                state = listState,
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CardBackground)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                            text = stringResource(id = R.string.select_apps),
                            style = SpaceGrotesk700Fs22,
                            color = TextColorDark
                        )
                    }
                }
                item {

                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = stringResource(id = R.string.select_apps_details),
                        style = NotoSans400Fs18,
                        color = TextColorDark
                    )
                    VerticalGap(24.dp)
                    androidx.compose.animation.AnimatedVisibility(
                        !uiState.isSearchAppClicked,
                        exit = slideOutVertically() + fadeOut()
                    ) {


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
                    }
                }
                item {
                    androidx.compose.animation.AnimatedVisibility(
                        uiState.isLoading,
                        enter = slideInVertically() + fadeIn(),
                        exit = slideOutVertically() + fadeOut()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 48.dp)
                                .clip(cardShape)
                                .background(CardBackground),
                            contentAlignment = Alignment.Center

                        ) {
                            CircularWavyProgressIndicator(
                                modifier = Modifier,
                                progress = { animatedProgress },
                                color = PrimaryBlueVariant,
                                trackColor = MidGrayBackground
                            )

                        }
                    }
                }

                if (uiState.isLoading == false && uiState.apps.isEmpty().not()) {
                    itemsIndexed(uiState.apps, key = { index, app ->
                        app.packageName
                    }) { index, app ->
                        androidx.compose.animation.AnimatedVisibility(
                            true,
                            enter = slideInVertically(
                                animationSpec = tween(durationMillis = 400),
                                initialOffsetY = { -40 }
                            ) + fadeIn(
                                animationSpec = tween(durationMillis = 400)
                            )) {
                            AppsItem(
                                appIcon = app.appIcon,
                                appName = app.appName,
                                checkValue = app.isChecked,
                                onCheckChanged = {
                                    onCheckChanged(app.packageName, it)
                                }
                            )
                        }
                    }
                }

            }


            ScrollToTop(scrollToTopButtonEnabled, onClicked = {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }, modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun AppsItem(
    modifier: Modifier = Modifier,
    appIcon: Bitmap?,
    appName: String,
    checkValue: Boolean,
    onCheckChanged: (Boolean) -> Unit
) {
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
        Text(
            text = appName,
            style = SpaceGrotesk700Fs18,
            color = TextColorDark,
            modifier = Modifier.weight(1f)
        )

        Switch(
            modifier = Modifier.scale(0.8f),
            checked = checkValue, onCheckedChange = { onCheckChanged(it) },
            thumbContent =
                {
                    Icon(
                        imageVector = if (checkValue) Icons.Filled.Check else Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                },
            colors = SwitchDefaults.colors(
                checkedThumbColor = White,
                checkedTrackColor = PrimaryBlueVariant,
                checkedIconColor = PrimaryBlueVariant,
                checkedBorderColor = PrimaryBlueVariant
            )
        )

    }

}


//@Preview
//@Composable
//private fun AppSelectionScreenPreview() {
//    AppSelectionScreen(uiState = AppListState())
//}