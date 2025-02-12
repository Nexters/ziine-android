package com.nexters.ziine.android.presentation.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.nexters.ziine.android.presentation.preview.ComponentPreview

private val DarkColorScheme = darkColorScheme(
    primary = Primary500,
    onPrimary = Gray900,
    primaryContainer = Primary200,
    background = Gray900,
    onBackground = Gray0,
    onSurface = Gray300,
    onSurfaceVariant = Gray600,
    outlineVariant = Gray700,
)

@Composable
fun ZiineTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = false
                isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content,
    )
}

