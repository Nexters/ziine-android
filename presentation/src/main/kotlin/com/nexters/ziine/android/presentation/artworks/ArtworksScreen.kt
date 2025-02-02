package com.nexters.ziine.android.presentation.artworks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun ArtworksRoute(modifier: Modifier = Modifier) {
    ArtworksScreen(
        modifier = modifier,
    )
}

@Composable
internal fun ArtworksScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "작품목록")
    }
}

@DevicePreview
@Composable
private fun ArtworksScreenPreview() {
    ZiineTheme {
        ArtworksScreen()
    }
}
