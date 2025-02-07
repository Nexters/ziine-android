package com.nexters.ziine.android.data.service

import com.nexters.ziine.android.data.httpClient.dto.response.ArtworkDetailResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ArtworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ZiineService {
    @GET("/api/v1/artworks")
    suspend fun fetchArtworks(): List<ArtworkResponse>

    @GET("/api/v1/artworks/{artwork_id}")
    suspend fun fetchArtworkDetail(
        @Query("artwork_id}") artworkId: Int,
    ): ArtworkDetailResponse
}
