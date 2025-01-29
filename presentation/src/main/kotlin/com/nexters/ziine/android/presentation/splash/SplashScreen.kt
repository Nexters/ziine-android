package com.nexters.ziine.android.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.preview.DevicePreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun SplashRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    SplashScreen(
        padding = padding,
        modifier = modifier,
    )
}

@Composable
internal fun SplashScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "스플래시")
    }
}

@DevicePreview
@Composable
private fun SplashScreenPreview() {
    ZiineTheme {
        SplashScreen(padding = PaddingValues(0.dp))
    }
}
