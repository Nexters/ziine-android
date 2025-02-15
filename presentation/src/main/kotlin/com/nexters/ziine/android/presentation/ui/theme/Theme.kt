package com.nexters.ziine.android.presentation.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Primary500,
    onPrimary = Gray900,
    secondaryContainer = Primary200,
    onSecondaryContainer = Color(0xFF000000),
    background = Gray900,
    onBackground = Gray0,
    onSurface = Gray300,
    onSurfaceVariant = Gray600,
    outline = Gray700,
    outlineVariant = Primary100,
    primaryContainer = Gray800,
    onPrimaryContainer = Color(0xFF7E7E7E),
    onSecondary = Gray500,
    onTertiary = Gray200,
    tertiaryContainer = Primary150,
    onTertiaryContainer = Color(0xFF6C6C6C),
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
