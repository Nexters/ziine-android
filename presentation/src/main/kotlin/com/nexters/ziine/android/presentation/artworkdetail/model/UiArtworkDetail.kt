package com.nexters.ziine.android.presentation.artworkdetail.model

data class UiArtworkDetail(
    val id: Int = 0,
    val title: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val material: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val artist: UiArtistDetail = UiArtistDetail(),
)
