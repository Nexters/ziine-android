package com.nexters.ziine.android.domain.entity

data class ArtworkDetail(
    val id: Int,
    val title: String,
    val width: Int,
    val height: Int,
    val material: String,
    val description: String,
    val imageUrl: String,
    val artist: ArtistDetail,
    val shareUrl: String,
)

data class ArtistDetail(
    val id: Int,
    val name: String,
    val profileImageUrl: String,
    val education: List<String>,
    val exhibition: List<Exhibition>,
    val contact: List<Contact>,
    val email: String,
)

data class Exhibition(
    val title: String,
    val exhibitionDate: String,
)

data class Contact(
    val type: String,
    val value: String,
)
