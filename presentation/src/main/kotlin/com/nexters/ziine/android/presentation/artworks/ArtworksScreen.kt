package com.nexters.ziine.android.presentation.artworks

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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiAction
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksUiState
import com.nexters.ziine.android.presentation.artworks.viewmodel.ArtworksViewModel
import com.nexters.ziine.android.presentation.preview.ArtworksPreviewParameterProvider
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun ArtworksRoute(artworksViewModel: ArtworksViewModel = hiltViewModel()) {
    val artworksUiState by artworksViewModel.uiState.collectAsStateWithLifecycle()

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
    val haptic = LocalHapticFeedback.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = uiState.artworks,
                key = { artwork -> artwork.id },
            ) { artwork ->
                ArtworkItem(
                    artwork = artwork,
                    onArtworkItemSelect = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onAction(ArtworksUiAction.OnArtworkItemSelect(artworkId = artwork.id))
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
