package com.nexters.ziine.android.presentation.registerArtwork.pending

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.nexters.ziine.android.presentation.component.RegisterTopBar
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun PendingRoute(modifier: Modifier = Modifier, activityFinishAction: () -> Unit) {
    PendingScreen(
        modifier = modifier,
        activityFinishAction
    )
}

@Composable
internal fun PendingScreen(modifier: Modifier = Modifier, activityFinishAction: () -> Unit) {
    val scrollState = rememberScrollState()
    var isScrolled by remember { mutableStateOf(false) }

    LaunchedEffect(scrollState.value) { isScrolled = scrollState.value > 0 }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        RegisterTopBar(isScrolled = isScrolled) { activityFinishAction() }
        GuideUI(scrollState)
    }
}

@Composable
private fun GuideUI(scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        
    }
}

@DevicePreview
@Composable
private fun PendingScreenPreview() {
    ZiineTheme {
        PendingScreen(activityFinishAction = {})
    }
}
