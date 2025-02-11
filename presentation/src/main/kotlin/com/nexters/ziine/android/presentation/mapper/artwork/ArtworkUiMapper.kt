package com.nexters.ziine.android.presentation.mapper.artwork

import com.nexters.ziine.android.domain.entity.Artist
import com.nexters.ziine.android.domain.entity.ArtistDetail
import com.nexters.ziine.android.domain.entity.ArtworkDetail
import com.nexters.ziine.android.domain.entity.Artwork
import com.nexters.ziine.android.domain.entity.Contact
import com.nexters.ziine.android.domain.entity.Exhibition
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtistDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiContact
import com.nexters.ziine.android.presentation.artworkdetail.model.UiExhibition
import com.nexters.ziine.android.presentation.artworks.model.UiArtist
import com.nexters.ziine.android.presentation.artworks.model.UiArtwork
import kotlinx.collections.immutable.toImmutableList

fun Artwork.toUiArtworks() =
    UiArtwork(
        id = id,
        title = title,
        artworkImageUrl = artworkImageUrl,
        artist = artist.toUiArtist()
    )

fun Artist.toUiArtist() =
    UiArtist(
        id = id,
        name = name,
        profileImageUrl = profileImageUrl,
    )

fun ArtworkDetail.toUiArtworkDetail() =
    UiArtworkDetail(
        id = id,
        title = title,
        width = width,
        height = height,
        material = material,
        description = description,
        artworkImageUrl = artworkImageUrl,
        artist = artist.toUiArtistDetail()
    )

fun ArtistDetail.toUiArtistDetail() =
    UiArtistDetail(
        id = id,
        name = name,
        profileImageUrl = profileImageUrl,
        education = education.toImmutableList(),
        exhibition = exhibition.map { it.toUiExhibition() }.toImmutableList(),
        contact = contact.map { it.toUiContact() }.toImmutableList(),
        email = email,
    )

fun Exhibition.toUiExhibition() =
    UiExhibition(
        title = title,
        exhibitionDate = exhibitionDate,
    )

fun Contact.toUiContact() =
    UiContact(
        type = type,
        value = value,
    )
