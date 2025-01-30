package com.nexters.ziine.android.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object ArtworkDetail : Route

    @Serializable
    data object MagazineDetail : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Artworks : MainTabRoute

    @Serializable
    data object Magazine : MainTabRoute
}
