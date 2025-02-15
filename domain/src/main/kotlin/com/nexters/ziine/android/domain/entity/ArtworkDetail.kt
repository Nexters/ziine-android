package com.nexters.ziine.android.domain.entity

data class ArtworkDetail(
    val id: Int,
    val title: String,
    val width: Int,
    val height: Int,
    val material: String,
    val description: String,
    val artworkImageUrl: String,
    val shareUrl: String,
    val artist: ArtistDetail,
)

data class ArtistDetail(
    val id: Int,
    val name: String,
    val profileImageUrl: String,
    val educations: List<String>,
    val exhibitions: List<Exhibition>,
    val contacts: List<Contact>,
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
