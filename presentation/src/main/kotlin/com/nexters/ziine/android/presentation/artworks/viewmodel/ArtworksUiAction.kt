package com.nexters.ziine.android.presentation.artworks.viewmodel

sealed interface ArtworksUiAction {
    data class OnArtworkItemSelect(
        val id: Int,
        val title: String,
        val artworkImageUrl: String,
    ) : ArtworksUiAction
}
