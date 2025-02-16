package com.nexters.ziine.android.data.repository

import com.nexters.ziine.android.data.httpClient.convertClassifiedResult
import com.nexters.ziine.android.data.mapper.artwork.toArtwork
import com.nexters.ziine.android.data.mapper.artwork.toArtworkDetail
import com.nexters.ziine.android.data.service.ZiineService
import com.nexters.ziine.android.domain.entity.ArtworkDetail
import com.nexters.ziine.android.domain.entity.Artworks
import com.nexters.ziine.android.domain.repository.ArtworkRepository
import javax.inject.Inject

class DefaultArtworkRepository
    @Inject
    constructor(
        private val service: ZiineService,
    ) : ArtworkRepository {
        override suspend fun fetchArtworks(): Result<Artworks> {
            return convertClassifiedResult {
                service.fetchArtworks().artworks.map { it.toArtwork() }.let { Artworks(it) }
            }
        }

        override suspend fun fetchArtworkDetail(id: Int): Result<ArtworkDetail> {
            return convertClassifiedResult {
                service.fetchArtworkDetail(id).toArtworkDetail()
            }
        }
    }
