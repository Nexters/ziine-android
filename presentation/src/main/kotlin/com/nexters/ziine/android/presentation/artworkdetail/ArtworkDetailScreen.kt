package com.nexters.ziine.android.presentation.artworkdetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import com.nexters.ziine.android.presentation.preview.ArtworksPreviewParameterProvider
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ArtworkDetailRoute(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    artworkDetailViewModel: ArtworkDetailViewModel = hiltViewModel(),
) {
    val artworkDetailUiState by artworkDetailViewModel.uiState.collectAsStateWithLifecycle()

    ArtworkDetailScreen(
        uiState = artworkDetailUiState,
        onAction = artworkDetailViewModel::onAction,
        animatedVisibilityScope = animatedVisibilityScope,
        sharedTransitionScope = sharedTransitionScope,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ArtworkDetailScreen(
    uiState: ArtworkDetailUiState,
    onAction: (ArtworkDetailUiAction) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        uiState.artwork?.let {
            ArtworkDetailItem(
                artwork = it,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
            )
        }
    }
}

// should fix preview not showing correctly because of SharedTransitionScope
@OptIn(ExperimentalSharedTransitionApi::class)
@DevicePreview
@Composable
private fun ArtworkDetailScreenPreview(
    @PreviewParameter(ArtworksPreviewParameterProvider::class)
    uiState: ArtworkDetailUiState,
) {
    ZiineTheme {
        SharedTransitionScope {
            AnimatedVisibility(visible = true) {
                ArtworkDetailScreen(
                    uiState = uiState,
                    onAction = {},
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionScope,
                )
            }
        }
    }
}
