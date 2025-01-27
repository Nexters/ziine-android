package com.nexters.ziine.android.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Artworks : Route

    @Serializable
    data object Magazine : Route
}