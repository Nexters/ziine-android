package com.nexters.ziine.android.presentation.artworks.viewmodel

sealed interface ArtworksUiAction {
    data class OnArtworkItemSelect(val artworkId: Int) : ArtworksUiAction
}
