package com.nexters.ziine.android.presentation.magazine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun MagazineRoute(modifier: Modifier = Modifier) {
    MagazineScreen(
        modifier = modifier,
    )
}

@Composable
internal fun MagazineScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "매거진")
    }
}

@DevicePreview
@Composable
private fun MagazineScreenPreview() {
    ZiineTheme {
        MagazineScreen()
    }
}
