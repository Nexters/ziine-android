package com.nexters.ziine.android.presentation.artworkdetail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexters.ziine.android.presentation.LocalSharedTransitionScope
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.artworkdetail.component.ArtistDescription
import com.nexters.ziine.android.presentation.artworkdetail.component.ArtworkDescription
import com.nexters.ziine.android.presentation.artworkdetail.component.ArtworkDetailItem
import com.nexters.ziine.android.presentation.artworkdetail.component.ArtworkDetailTopBar
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailUiAction
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailUiEvent
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailUiState
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailViewModel
import com.nexters.ziine.android.presentation.common.util.ObserveAsEvents
import com.nexters.ziine.android.presentation.component.ZiineErrorDialog
import com.nexters.ziine.android.presentation.component.ZiineSnackbar
import com.nexters.ziine.android.presentation.preview.ArtworkDetailPreviewParameterProvider
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.Gray900
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController

@Composable
internal fun ArtworkDetailRoute(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    artworkDetailViewModel: ArtworkDetailViewModel = hiltViewModel()
) {
    val artworkDetailUiState by artworkDetailViewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val systemUiController = rememberExSystemUiController()
    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
    var isScrollPositionChanged by remember { mutableStateOf(false) }

    DisposableEffect(systemUiController, isScrollPositionChanged) {
        systemUiController.setSystemBarsColor(
            color = if (isScrollPositionChanged) Gray900 else Color.Transparent,
            darkIcons = false,
        )

        onDispose {
            systemUiController.setSystemBarsColor(
                color = Gray900,
                darkIcons = false,
            )
        }
    }

    ObserveAsEvents(flow = artworkDetailViewModel.uiEvent) { event ->
        when (event) {
            is ArtworkDetailUiEvent.NavigateBack -> popBackStack()
            is ArtworkDetailUiEvent.ShareUrl -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, event.url)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }

            is ArtworkDetailUiEvent.CopyValue -> {
                val clipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("value", event.value)
                clipboardManager.setPrimaryClip(clip)

//                val snackbarMessage = if (event.type == context.getString(R.string.instagram)) {
//                    context.getString(R.string.instagram_id_has_been_copied)
//                } else {
//                    context.getString(R.string.link_has_been_copied)
//                }
//
//                scope.launch {
//                    snackbarHostState.showSnackbar(snackbarMessage)
//                }
            }
        }
    }

    ArtworkDetailScreen(
        padding = padding,
        uiState = artworkDetailUiState,
        onAction = artworkDetailViewModel::onAction,
        snackbarHostState = snackbarHostState,
        animatedVisibilityScope = animatedVisibilityScope,
        onScrollPositionChanged = { isScrollPositionChanged = it }
    )
}

@Composable
internal fun ArtworkDetailScreen(
    padding: PaddingValues,
    uiState: ArtworkDetailUiState,
    onAction: (ArtworkDetailUiAction) -> Unit,
    snackbarHostState: SnackbarHostState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onScrollPositionChanged: (Boolean) -> Unit,
) {
    val scrollState = rememberLazyListState()
    val isAtTop by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex == 0 && scrollState.firstVisibleItemScrollOffset == 0
        }
    }

    LaunchedEffect(isAtTop) {
        onScrollPositionChanged(!isAtTop)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = padding.calculateBottomPadding()),
    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                ArtworkDetailItem(
                    artworkImageUrl = uiState.artworkDetail.artworkImageUrl,
                    animatedVisibilityScope = animatedVisibilityScope,
                )
            }
            item {
                ArtworkDescription(
                    artworkDetail = uiState.artworkDetail,
                    onShareClick = { url -> onAction(ArtworkDetailUiAction.OnShareClick(url)) },
                    animatedVisibilityScope = animatedVisibilityScope,
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                ArtistDescription(
                    artistDetail = uiState.artworkDetail.artist,
                    onCopyClick = { type, value ->
                        onAction(ArtworkDetailUiAction.OnCopyClick(type, value))
                    },
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        ArtworkDetailTopBar(
            onBackClick = { onAction(ArtworkDetailUiAction.OnBackClick) },
            isAtTop = isAtTop,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = padding.calculateTopPadding()),
        )

        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomCenter),
            hostState = snackbarHostState,
            snackbar = { ZiineSnackbar(message = it.visuals.message) },
        )

        ZiineErrorDialog(
            isErrorDialogVisible = uiState.isError,
            onDismissRequest = {},
            titleResId = R.string.error_title,
            descriptionResId = R.string.error_description,
            onRetryClick = { onAction(ArtworkDetailUiAction.OnRetryClick) },
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@DevicePreview
@Composable
private fun ArtworkDetailScreenPreview(
    @PreviewParameter(ArtworkDetailPreviewParameterProvider::class)
    uiState: ArtworkDetailUiState,
) {
    ZiineTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                ) {
                    ArtworkDetailScreen(
                        padding = PaddingValues(),
                        uiState = uiState,
                        onAction = {},
                        snackbarHostState = remember { SnackbarHostState() },
                        animatedVisibilityScope = this@AnimatedVisibility,
                        onScrollPositionChanged = {},
                    )
                }
            }
        }
    }
}
