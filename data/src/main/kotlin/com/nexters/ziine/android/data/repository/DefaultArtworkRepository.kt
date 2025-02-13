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
//                listOf(
//                    ArtworkResponse(
//                        id = 1,
//                        title = "Artwork 1",
//                        artworkImageUrl = "https://placehold.co/600x400/png",
//                        artist = ArtistResponse(
//                            id = 1,
//                            name = "Artist 1",
//                            profileImageUrl = "https://example.com/profile1.png",
//                        ),
//                    ),
//                    ArtworkResponse(
//                        id = 2,
//                        title = "Artwork 2",
//                        artworkImageUrl = "https://placehold.co/400x600/png",
//                        artist = ArtistResponse(
//                            id = 2,
//                            name = "Artist 2",
//                            profileImageUrl = "https://example.com/profile2.png",
//                        ),
//                    ),
//                    ArtworkResponse(
//                        id = 3,
//                        title = "Artwork 3",
//                        artworkImageUrl = "https://placehold.co/500x500/png",
//                        artist = ArtistResponse(
//                            id = 3,
//                            name = "Artist 3",
//                            profileImageUrl = "https://example.com/profile3.png",
//                        ),
//                    ),
//                    ArtworkResponse(
//                        id = 4,
//                        title = "Artwork 4",
//                        artworkImageUrl = "https://placehold.co/300x500/png",
//                        artist = ArtistResponse(
//                            id = 4,
//                            name = "Artist 4",
//                            profileImageUrl = "https://example.com/profile4.png",
//                        ),
//                    ),
//                    ArtworkResponse(
//                        id = 5,
//                        title = "Artwork 5",
//                        artworkImageUrl = "https://placehold.co/500x300/png",
//                        artist = ArtistResponse(
//                            id = 5,
//                            name = "Artist 5",
//                            profileImageUrl = "https://example.com/profile5.png",
//                        ),
//                    ),
//                    ArtworkResponse(
//                        id = 6,
//                        title = "Artwork 6",
//                        artworkImageUrl = "https://placehold.co/400x800/png",
//                        artist = ArtistResponse(
//                            id = 6,
//                            name = "Artist 6",
//                            profileImageUrl = "https://example.com/profile1.png",
//                        ),
//                    ),
//                ).map { it.toArtwork() }
            }
        }

        override suspend fun fetchArtworkDetail(id: Int): Result<ArtworkDetail> {
            return convertClassifiedResult {
                service.fetchArtworkDetail(id).toArtworkDetail()
//                ArtworkDetailResponse(
//                    id = id,
//                    title = "",
//                    width = 100,
//                    height = 100,
//                    material = "페인트",
//                    description = "진짜 진짜 열심히 그림(진짜임)",
//                    artworkImageUrl = "",
//                    artist = ArtistDetailResponse(
//                        id = 1,
//                        name = "멧돼지",
//                        profileImageUrl = "https://example.com/profile1/png",
//                        education = listOf("세종대학교", "동양학과"),
//                        exhibition = listOf(
//                            ExhibitionResponse(
//                                title = "세종대학교 졸업전시회",
//                                exhibitionDate = "2025.02.01",
//                            ),
//                        ),
//                        contact = listOf(
//                            ContactResponse(
//                                type = "INSTAGRAM",
//                                value = "y_joo_z",
//                            ),
//                        ),
//                        email = "yjoo@ziine.com",
//                    ),
//                    shareUrl = "https://m.naver.com",
//                ).toArtworkDetail()
            }
        }
    }
