package com.nexters.ziine.android.presentation.artworks

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.MainTabRoute

fun NavController.navigateToArtworks(navOptions: NavOptions) {
    navigate(MainTabRoute.Artworks, navOptions)
}

fun NavGraphBuilder.artworksScreen(modifier: Modifier = Modifier) {
    composable<MainTabRoute.Artworks> {
        ArtworksRoute(
            modifier = modifier,
        )
    }
}
