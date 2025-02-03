package com.nexters.ziine.android.presentation.artworkdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailUiAction
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailUiState
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailViewModel
import com.nexters.ziine.android.presentation.artworks.ArtworkItem
import com.nexters.ziine.android.presentation.preview.ArtworksPreviewParameterProvider
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun ArtworkDetailRoute(artworkDetailViewModel: ArtworkDetailViewModel = hiltViewModel()) {
    val artworkDetailUiState by artworkDetailViewModel.uiState.collectAsStateWithLifecycle()

    ArtworkDetailScreen(
        uiState = artworkDetailUiState,
        onAction = artworkDetailViewModel::onAction,
    )
}

@Composable
internal fun ArtworkDetailScreen(
    uiState: ArtworkDetailUiState,
    onAction: (ArtworkDetailUiAction) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        uiState.artwork?.let {
            ArtworkItem(
                artwork = it,
                onArtworkItemSelect = {},
            )
        }
    }
}

@DevicePreview
@Composable
private fun ArtworkDetailScreenPreview(
    @PreviewParameter(ArtworksPreviewParameterProvider::class)
    uiState: ArtworkDetailUiState,
) {
    ZiineTheme {
        ArtworkDetailScreen(
            uiState = uiState,
            onAction = {},
        )
    }
}
