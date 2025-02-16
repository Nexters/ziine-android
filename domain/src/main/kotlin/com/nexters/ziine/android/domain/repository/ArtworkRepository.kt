package com.nexters.ziine.android.domain.repository

import com.nexters.ziine.android.domain.entity.ArtworkDetail
import com.nexters.ziine.android.domain.entity.Artworks

interface ArtworkRepository {
    suspend fun fetchArtworks(): Result<Artworks>

    suspend fun fetchArtworkDetail(id: Int): Result<ArtworkDetail>
}
