package com.nexters.ziine.android.presentation.artworks.model

data class UiArtwork(
    val id: Int,
    val title: String,
    val artworkImageUrl: String,
    val artist: UiArtist,
)

data class UiArtist(
    val id: Int,
    val name: String,
    val profileImageUrl: String,
)
