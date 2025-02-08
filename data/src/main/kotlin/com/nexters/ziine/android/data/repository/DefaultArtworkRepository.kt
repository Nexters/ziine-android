package com.nexters.ziine.android.data.repository

import com.nexters.ziine.android.data.httpClient.convertClassifiedResult
import com.nexters.ziine.android.data.httpClient.dto.response.ArtistDetailResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ArtistResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ArtworkDetailResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ArtworkResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ContactResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ExhibitionResponse
import com.nexters.ziine.android.data.mapper.artwork.toArtwork
import com.nexters.ziine.android.data.mapper.artwork.toArtworkDetail
import com.nexters.ziine.android.data.service.ZiineService
import com.nexters.ziine.android.domain.entity.Artwork
import com.nexters.ziine.android.domain.entity.ArtworkDetail
import com.nexters.ziine.android.domain.repository.ArtworkRepository
import javax.inject.Inject

class DefaultArtworkRepository
    @Inject
    constructor(
        private val service: ZiineService,
    ) : ArtworkRepository {
        override suspend fun fetchArtworks(): Result<List<Artwork>> {
            return convertClassifiedResult {
                // service.fetchArtworks().map { it.toArtwork() }
                listOf(
                    ArtworkResponse(
                        id = 1,
                        artist = ArtistResponse(
                            id = 1,
                            name = "Artist 1",
                            profileImageUrl = "https://example.com/profile1.png",
                        ),
                        imageUrl = "https://placehold.co/600x400/png",
                        title = "Artwork 1",
                    ),
                    ArtworkResponse(
                        id = 2,
                        artist = ArtistResponse(
                            id = 2,
                            name = "Artist 2",
                            profileImageUrl = "https://example.com/profile2.png",
                        ),
                        imageUrl = "https://placehold.co/400x600/png",
                        title = "Artwork 2",
                    ),
                    ArtworkResponse(
                        id = 3,
                        artist = ArtistResponse(
                            id = 3,
                            name = "Artist 3",
                            profileImageUrl = "https://example.com/profile3.png",
                        ),
                        imageUrl = "https://placehold.co/500x500/png",
                        title = "Artwork 3",
                    ),
                    ArtworkResponse(
                        id = 4,
                        artist = ArtistResponse(
                            id = 4,
                            name = "Artist 4",
                            profileImageUrl = "https://example.com/profile4.png",
                        ),
                        imageUrl = "https://placehold.co/300x500/png",
                        title = "Artwork 4",
                    ),
                    ArtworkResponse(
                        id = 5,
                        artist = ArtistResponse(
                            id = 5,
                            name = "Artist 5",
                            profileImageUrl = "https://example.com/profile5.png",
                        ),
                        imageUrl = "https://placehold.co/500x300/png",
                        title = "Artwork 5",
                    ),
                    ArtworkResponse(
                        id = 6,
                        artist = ArtistResponse(
                            id = 6,
                            name = "Artist 6",
                            profileImageUrl = "https://example.com/profile1.png",
                        ),
                        imageUrl = "https://placehold.co/400x800/png",
                        title = "Artwork 6",
                    ),
                ).map { it.toArtwork() }
            }
        }

        override suspend fun fetchArtworkDetail(id: Int): Result<ArtworkDetail> {
            return convertClassifiedResult {
                // service.fetchArtworkDetail(id).toArtworkDetail()
                ArtworkDetailResponse(
                    id = id,
                    title = "",
                    imageUrl = "",
                    width = 100,
                    height = 100,
                    material = "페인트",
                    description = "진짜 진짜 열심히 그림(진짜임)",
                    artist = ArtistDetailResponse(
                        id = 1,
                        name = "멧돼지",
                        profileImageUrl = "https://example.com/profile1/png",
                        education = listOf("세종대학교", "동양학과"),
                        exhibition = listOf(
                            ExhibitionResponse(
                                title = "세종대학교 졸업전시회",
                                exhibitionDate = "2025.02.01",
                            ),
                        ),
                        contact = listOf(
                            ContactResponse(
                                type = "INSTAGRAM",
                                value = "y_joo_z",
                            ),
                        ),
                        email = "yjoo@ziine.com",
                    ),
                    shareUrl = "https://m.naver.com",
                ).toArtworkDetail()
            }
        }
    }
