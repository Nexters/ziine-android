package com.nexters.ziine.android.presentation.registerArtwork.navigation

import kotlinx.serialization.Serializable

sealed interface RegisterArtWorkRoute {
    @Serializable
    object Pending : RegisterArtWorkRoute

    @Serializable
    object Register : RegisterArtWorkRoute

    @Serializable
    object Complete : RegisterArtWorkRoute
}
