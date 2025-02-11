package com.nexters.ziine.android.presentation.artworks.viewmodel

sealed interface ArtworksUiEvent {
    data class NavigateToArtworkDetail(
        val id: Int,
        val imageUrl: String,
        val title: String,
    ) : ArtworksUiEvent
}
