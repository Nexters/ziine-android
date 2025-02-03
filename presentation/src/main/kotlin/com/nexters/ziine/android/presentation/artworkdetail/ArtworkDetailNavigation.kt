package com.nexters.ziine.android.presentation.artworkdetail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.Route

fun NavController.navigateToArtworkDetail(
    id: Int,
    artistName: String,
    imageUrl: String,
    title: String
) {
    navigate(Route.ArtworkDetail(id, artistName, imageUrl, title))
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.artworkDetailScreen(sharedTransitionScope: SharedTransitionScope) {
    composable<Route.ArtworkDetail> {
        ArtworkDetailRoute(
            animatedVisibilityScope = this@composable,
            sharedTransitionScope = sharedTransitionScope,
        )
    }
}
