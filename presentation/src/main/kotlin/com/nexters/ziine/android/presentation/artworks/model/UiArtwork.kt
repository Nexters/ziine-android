package com.nexters.ziine.android.presentation.artworks.model

data class UiArtwork(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val artist: UiArtist,
)
