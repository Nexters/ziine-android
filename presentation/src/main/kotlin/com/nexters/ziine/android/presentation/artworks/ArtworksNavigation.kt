package com.nexters.ziine.android.presentation.artworks

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.MainTabRoute

fun NavController.navigateToArtworks(navOptions: NavOptions) {
    navigate(MainTabRoute.Artworks, navOptions)
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.artworksScreen(
    navigateToArtworkDetail: (Int, String, String, String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
) {
    composable<MainTabRoute.Artworks> {
        ArtworksRoute(
            navigateToArtworkDetail = navigateToArtworkDetail,
            animatedVisibilityScope = this@composable,
            sharedTransitionScope = sharedTransitionScope,
        )
    }
}
