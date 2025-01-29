package com.nexters.ziine.android.presentation.artworks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nexters.ziine.android.presentation.navigation.Route

fun NavGraphBuilder.artworksScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<Route.Artworks> {
        ArtworksRoute(
            padding = padding,
            modifier = modifier,
        )
    }
}
