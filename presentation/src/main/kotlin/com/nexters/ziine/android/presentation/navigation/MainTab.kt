package com.nexters.ziine.android.presentation.navigation

import androidx.compose.runtime.Composable

enum class MainTab(
    val label: String,
    val route: MainTabRoute,
) {
    ARTWORKS(
        label = "Artworks",
        route = MainTabRoute.Artworks,
    ),
    MAGAZINE(
        label = "Magazine",
        route = MainTabRoute.Magazine,
    ),
    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
