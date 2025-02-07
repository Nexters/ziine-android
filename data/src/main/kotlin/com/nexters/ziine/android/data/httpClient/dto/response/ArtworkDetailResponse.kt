package com.nexters.ziine.android.data.httpClient.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtworkDetailResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int = 0,
    @SerialName("material")
    val material: String,
    @SerialName("description")
    val description: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("artist")
    val artist: ArtistDetailResponse,
)

@Serializable
data class ArtistDetailResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
    @SerialName("education")
    val education: List<String>,
    @SerialName("exhibition")
    val exhibition: List<ExhibitionResponse>,
    @SerialName("contact")
    val contact: List<ContactResponse>,
    @SerialName("email")
    val email: String,
)

@Serializable
data class ExhibitionResponse(
    @SerialName("title")
    val title: String,
    @SerialName("exhibitionDate")
    val exhibitionDate: String,
)

@Serializable
data class ContactResponse(
    @SerialName("type")
    val type: String,
    @SerialName("value")
    val value: String,
)
