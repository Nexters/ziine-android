package com.nexters.ziine.android.presentation.magazinedetail

import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexters.ziine.android.presentation.common.util.ObserveAsEvents
import com.nexters.ziine.android.presentation.common.webViews.ComposeWrappedWebView
import com.nexters.ziine.android.presentation.component.RegisterTopBar
import com.nexters.ziine.android.presentation.magazinedetail.viewmodel.MagazineDetailUiAction
import com.nexters.ziine.android.presentation.magazinedetail.viewmodel.MagazineDetailUiEvent
import com.nexters.ziine.android.presentation.magazinedetail.viewmodel.MagazineDetailViewModel
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun MagazineDetailRoute(
    padding: PaddingValues,
    backToPrevious: () -> Unit,
    magazineDetailViewModel: MagazineDetailViewModel = hiltViewModel(),
) {
    ObserveAsEvents(flow = magazineDetailViewModel.uiEvent) { event ->
        when (event) {
            is MagazineDetailUiEvent.BackToPrevious -> backToPrevious()
        }
    }

    MagazineDetailScreen(
        padding = padding,
        magazineId = magazineDetailViewModel.magazineId,
        onAction = magazineDetailViewModel::onAction,
    )
}

@Composable
internal fun MagazineDetailScreen(
    padding: PaddingValues,
    magazineId: Int,
    onAction: (MagazineDetailUiAction) -> Unit,
) {
    val (isScrolled, setIsScrolled) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    ) {
        RegisterTopBar(isScrolled = isScrolled) { onAction(MagazineDetailUiAction.OnBackButtonClicked) }
        ComposeWrappedWebViewPairedWithTopBar(
            magazineId = magazineId,
            modifier = Modifier.weight(1f),
            setIsScrolled = setIsScrolled,
        )
    }
}

@Composable
private fun ComposeWrappedWebViewPairedWithTopBar(
    magazineId: Int,
    modifier: Modifier = Modifier,
    setIsScrolled: (Boolean) -> Unit,
) {
    ComposeWrappedWebView(modifier = modifier.fillMaxSize()) { context ->
        WebView(context).setRegisterSettingsToWebView(setIsScrolled).apply {
            loadUrl("https://www.ziine.gallery/magazine-webview/$magazineId")
        }
    }
}

private fun WebView.setRegisterSettingsToWebView(setIsScrolled: (Boolean) -> Unit): WebView {
    setOnScrollChangeListener { _, _, scrollY, _, _ ->
        setIsScrolled(scrollY > 0)
    }
    return this
}

@DevicePreview
@Composable
private fun  MagazineDetailScreenPreview() {
    ZiineTheme {
        MagazineDetailScreen(
            padding = PaddingValues(),
            onAction = {},
            magazineId = 1,
        )
    }
}

