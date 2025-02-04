package com.nexters.ziine.android.presentation.registerArtwork.pending

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun PendingRoute(modifier: Modifier = Modifier) {
    PendingScreen(
        modifier = modifier,
    )
}

@Composable
internal fun PendingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "등록대기")
    }
}

@DevicePreview
@Composable
private fun PendingScreenPreview() {
    ZiineTheme {
        PendingScreen()
    }
}
