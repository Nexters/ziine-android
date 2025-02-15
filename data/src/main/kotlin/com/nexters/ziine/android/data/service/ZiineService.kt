package com.nexters.ziine.android.data.service

import com.nexters.ziine.android.data.httpClient.dto.response.ArtworkDetailResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ArtworksResponse
import com.nexters.ziine.android.data.httpClient.dto.response.MagazineResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ZiineService {
    @GET("/api/v1/artworks")
    suspend fun fetchArtworks(): ArtworksResponse

    @GET("/api/v1/artworks/{artwork_id}")
    suspend fun fetchArtworkDetail(
        @Path("artwork_id") artworkId: Int,
    ): ArtworkDetailResponse

    @GET("api/v1/magazines")
    suspend fun fetchMagazines(): MagazineResponse
}
