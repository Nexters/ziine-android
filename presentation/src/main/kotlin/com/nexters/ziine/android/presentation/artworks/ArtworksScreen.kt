package com.nexters.ziine.android.presentation.artworks

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexters.ziine.android.presentation.LocalSharedTransitionScope
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiAction
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiEvent
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiState
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksViewModel
import com.nexters.ziine.android.presentation.common.util.ObserveAsEvents
import com.nexters.ziine.android.presentation.component.LoadingIndicator
import com.nexters.ziine.android.presentation.component.ZiineErrorDialog
import com.nexters.ziine.android.presentation.preview.ArtworksPreviewParameterProvider
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun ArtworksRoute(
    padding: PaddingValues,
    navigateToArtworkDetail: (Int, String, String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    artworksViewModel: ArtworksViewModel = hiltViewModel(),
) {
    val artworksUiState by artworksViewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = artworksViewModel.uiEvent) { event ->
        when (event) {
            is ArtworksUiEvent.NavigateToArtworkDetail -> {
                navigateToArtworkDetail(
                    event.id,
                    event.title,
                    event.artworkImageUrl,
                )
            }
        }
    }

    ArtworksScreen(
        padding = padding,
        uiState = artworksUiState,
        onAction = artworksViewModel::onAction,
        animatedVisibilityScope = animatedVisibilityScope,
    )
}

@Composable
internal fun ArtworksScreen(
    padding: PaddingValues,
    uiState: ArtworksUiState,
    onAction: (ArtworksUiAction) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val context = LocalContext.current
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 16.dp, bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = uiState.artworks,
                key = { artwork -> artwork.id },
            ) { artwork ->
                ArtworkItem(
                    artwork = artwork,
                    onArtworkItemSelect = {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                        onAction(
                            ArtworksUiAction.OnArtworkItemSelect(
                                id = artwork.id,
                                title = artwork.title,
                                artworkImageUrl = artwork.artworkImageUrl,
                            ),
                        )
                    },
                    animatedVisibilityScope = animatedVisibilityScope,
                )
            }
        }

        LoadingIndicator(isLoading = uiState.isLoading)

        ZiineErrorDialog(
            isErrorDialogVisible = uiState.isError,
            onDismissRequest = {},
            titleResId = R.string.error_title,
            descriptionResId = R.string.error_description,
            onRetryClick = { onAction(ArtworksUiAction.OnRetryClick) },
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@DevicePreview
@Composable
private fun ArtworksScreenPreview(
    @PreviewParameter(ArtworksPreviewParameterProvider::class)
    uiState: ArtworksUiState,
) {
    ZiineTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                ) {
                    ArtworksScreen(
                        padding = PaddingValues(),
                        uiState = uiState,
                        onAction = {},
                        animatedVisibilityScope = this@AnimatedVisibility,
                    )
                }
            }
        }
    }
}
