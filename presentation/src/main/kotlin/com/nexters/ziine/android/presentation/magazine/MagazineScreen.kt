package com.nexters.ziine.android.presentation.magazine

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.artworks.ArtworksScreen
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun MagazineRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    MagazineScreen(
        padding = padding,
        modifier = modifier,
    )
}

@Composable
internal fun MagazineScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "매거진")
    }
}

@DevicePreview
@Composable
private fun MagazineScreenPreview() {
    ZiineTheme {
        MagazineScreen(padding = PaddingValues(0.dp))
    }
}