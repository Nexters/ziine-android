package com.nexters.ziine.android.presentation.splash

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.Route

fun NavGraphBuilder.splashScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<Route.Splash> {
        SplashRoute(
            padding = padding,
            modifier = modifier,
        )
    }
}
