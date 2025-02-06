package com.nexters.ziine.android.presentation.artworks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.MainTabRoute

fun NavController.navigateToArtworks(navOptions: NavOptions) {
    navigate(MainTabRoute.Artworks, navOptions)
}

fun NavGraphBuilder.artworksScreen(navigateToArtworkDetail: (Int, String, String) -> Unit) {
    composable<MainTabRoute.Artworks> {
        ArtworksRoute(
            navigateToArtworkDetail = navigateToArtworkDetail,
            animatedVisibilityScope = this@composable,
        )
    }
}
