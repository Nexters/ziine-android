package com.nexters.ziine.android.domain.repository

import com.nexters.ziine.android.domain.entity.Artwork
import com.nexters.ziine.android.domain.entity.ArtworkDetail

interface ArtworkRepository {
    suspend fun fetchArtworks(): Result<List<Artwork>>

    suspend fun fetchArtworkDetail(id: Int): Result<ArtworkDetail>
}
