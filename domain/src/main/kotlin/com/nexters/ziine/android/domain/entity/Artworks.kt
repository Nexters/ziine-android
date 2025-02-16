package com.nexters.ziine.android.domain.entity

data class Artworks(
    val artworks: List<Artwork>,
)

data class Artwork(
    val id: Int,
    val title: String,
    val artworkImageUrl: String,
    val artist: Artist,
)

data class Artist(
    val id: Int,
    val name: String,
    val profileImageUrl: String,
)
