package com.nexters.ziine.android.presentation.artworks

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiAction
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiEvent
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiState
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksViewModel
import com.nexters.ziine.android.presentation.common.util.ObserveAsEvents
import com.nexters.ziine.android.presentation.preview.ArtworksPreviewParameterProvider
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun ArtworksRoute(
    navigateToArtworkDetail: (Int, String, String, String) -> Unit,
    artworksViewModel: ArtworksViewModel = hiltViewModel(),
) {
    val artworksUiState by artworksViewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = artworksViewModel.uiEvent) { event ->
        when (event) {
            is ArtworksUiEvent.NavigateToArtworkDetail -> {
                navigateToArtworkDetail(
                    event.id,
                    event.artistName,
                    event.imageUrl,
                    event.title,
                )
            }
        }
    }

    ArtworksScreen(
        uiState = artworksUiState,
        onAction = artworksViewModel::onAction,
    )
}

@Composable
internal fun ArtworksScreen(
    uiState: ArtworksUiState,
    onAction: (ArtworksUiAction) -> Unit,
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
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
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
                                artwork.id,
                                artwork.artistName,
                                artwork.imageUrl,
                                artwork.title,
                            ),
                        )
                    },
                )
            }
        }
    }
}

@DevicePreview
@Composable
private fun ArtworksScreenPreview(
    @PreviewParameter(ArtworksPreviewParameterProvider::class)
    uiState: ArtworksUiState,
) {
    ZiineTheme {
        ArtworksScreen(
            uiState = uiState,
            onAction = {},
        )
    }
}
