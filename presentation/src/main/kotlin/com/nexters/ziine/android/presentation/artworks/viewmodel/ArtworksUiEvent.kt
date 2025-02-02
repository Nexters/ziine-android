package com.nexters.ziine.android.presentation.artworks.viewmodel

sealed interface ArtworksUiEvent {
    data class NavigateToArtworkDetail(val artworkId: Int) : ArtworksUiEvent
}
