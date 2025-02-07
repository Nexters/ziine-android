package com.nexters.ziine.android.domain.entity

data class Artwork(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val artist: Artist,
)

data class Artist(
    val id: Int,
    val name: String,
    val profileImageUrl: String,
)
