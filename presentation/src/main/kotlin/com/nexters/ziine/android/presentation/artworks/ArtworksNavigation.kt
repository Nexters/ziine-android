package com.nexters.ziine.android.presentation.artworks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.LocalNavAnimatedVisibilityScope
import com.nexters.ziine.android.presentation.navigation.MainTabRoute

fun NavController.navigateToArtworks(navOptions: NavOptions) {
    navigate(MainTabRoute.Artworks, navOptions)
}

fun NavGraphBuilder.artworksScreen(
    padding: PaddingValues,
    navigateToArtworkDetail: (Int, String, String) -> Unit
) {
    composable<MainTabRoute.Artworks> {
        CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this@composable) {
            ArtworksRoute(
                padding = padding,
                navigateToArtworkDetail = navigateToArtworkDetail,
            )
        }
    }
}
