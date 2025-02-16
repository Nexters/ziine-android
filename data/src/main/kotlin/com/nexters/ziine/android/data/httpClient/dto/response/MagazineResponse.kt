package com.nexters.ziine.android.data.httpClient.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MagazineResponse(
    @SerialName("magazines")
    val magazines: List<Magazine>,
    @SerialName("totalCount")
    val totalCount: Int
) {
    @Serializable
    data class Magazine(
        @SerialName("backgroundColor")
        val backgroundColor: String,
        @SerialName("createdAt")
        val createdAt: String,
        @SerialName("id")
        val id: Int,
        @SerialName("keywords")
        val keywords: List<String>,
        @SerialName("modifiedAt")
        val modifiedAt: String,
        @SerialName("summary")
        val summary: String,
        @SerialName("thumbnailImageUrl")
        val thumbnailImageUrl: String,
        @SerialName("title")
        val title: String
    )
}
