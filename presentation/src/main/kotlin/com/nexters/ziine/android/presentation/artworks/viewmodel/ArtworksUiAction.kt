package com.nexters.ziine.android.presentation.artworks.viewmodel

sealed interface ArtworksUiAction {
    data class OnArtworkItemSelect(
        val id: Int,
        val artistName: String,
        val imageUrl: String,
        val title: String,
    ) : ArtworksUiAction
}
