package com.nexters.ziine.android.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class ArtworkDetail(
        val id: Int,
        val title: String,
        val artworkImageUrl: String,
    ) : Route

    @Serializable
    data class MagazineDetail(val magazineId: Int) : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Artworks : MainTabRoute

    @Serializable
    data object Magazine : MainTabRoute
}
