package com.nexters.ziine.android.data.httpClient.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtworksResponse(
    @SerialName("artworks")
    val artworks: List<ArtworkResponse>,
)

@Serializable
data class ArtworkResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("artworkImageUrl")
    val artworkImageUrl: String,
    @SerialName("artist")
    val artist: ArtistResponse,
)

@Serializable
data class ArtistResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
)
