package com.nexters.ziine.android.data.repository

import com.nexters.ziine.android.data.mapper.artwork.toArtwork
import com.nexters.ziine.android.data.mapper.artwork.toArtworkDetail
import com.nexters.ziine.android.data.service.ZiineService
import com.nexters.ziine.android.domain.entity.Artwork
import com.nexters.ziine.android.domain.entity.ArtworkDetail
import com.nexters.ziine.android.domain.repository.ArtworkRepository

class DefaultArtworkRepository(
    private val service: ZiineService,
) : ArtworkRepository {
    override suspend fun fetchArtworks(): List<Artwork> {
        return service.fetchArtworks().map { it.toArtwork() }
    }

    override suspend fun fetchArtworkDetail(id: Int): ArtworkDetail {
        return service.fetchArtworkDetail(id).toArtworkDetail()
    }
}

