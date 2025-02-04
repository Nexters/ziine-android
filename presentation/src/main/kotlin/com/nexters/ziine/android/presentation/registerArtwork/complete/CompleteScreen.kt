package com.nexters.ziine.android.presentation.registerArtwork.complete

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun CompleteRoute(modifier: Modifier = Modifier) {
    CompleteScreen(
        modifier = modifier,
    )
}

@Composable
internal fun CompleteScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "등록완료")
    }
}

@DevicePreview
@Composable
private fun CompleteScreenPreview() {
    ZiineTheme {
        CompleteScreen()
    }
}
