package com.nexters.ziine.android.data.mapper.artwork

import com.nexters.ziine.android.data.httpClient.dto.response.ArtistDetailResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ArtistResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ArtworkDetailResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ArtworkResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ContactResponse
import com.nexters.ziine.android.data.httpClient.dto.response.ExhibitionResponse
import com.nexters.ziine.android.domain.entity.Artist
import com.nexters.ziine.android.domain.entity.ArtistDetail
import com.nexters.ziine.android.domain.entity.ArtworkDetail
import com.nexters.ziine.android.domain.entity.Artwork
import com.nexters.ziine.android.domain.entity.Contact
import com.nexters.ziine.android.domain.entity.Exhibition

fun ArtworkResponse.toArtwork() =
    Artwork(
        id = id,
        title = title,
        artworkImageUrl = artworkImageUrl,
        artist = artist.toArtist()
    )

fun ArtistResponse.toArtist() =
    Artist(
        id = id,
        name = name,
        profileImageUrl = profileImageUrl,
    )

fun ArtworkDetailResponse.toArtworkDetail() =
    ArtworkDetail(
        id = id,
        title = title,
        width = width,
        height = height,
        material = material,
        description = description,
        artworkImageUrl = artworkImageUrl,
        artist = artist.toArtistDetail(),
        shareUrl = shareUrl,
    )

fun ArtistDetailResponse.toArtistDetail() =
    ArtistDetail(
        id = id,
        name = name,
        profileImageUrl = profileImageUrl,
        educations = educations,
        exhibitions = exhibitions.map { it.toExhibition() },
        contacts = contacts.map { it.toContact() },
        email = email,
    )

fun ExhibitionResponse.toExhibition() =
    Exhibition(
        title = title,
        exhibitionDate = exhibitionDate,
    )

fun ContactResponse.toContact() =
    Contact(
        type = type,
        value = value,
    )
