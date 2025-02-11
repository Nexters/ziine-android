package com.nexters.ziine.android.presentation.artworkdetail.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class UiArtistDetail(
    val id: Int = 0,
    val name: String = "",
    val profileImageUrl: String = "",
    val education: ImmutableList<String> = persistentListOf(),
    val exhibition: ImmutableList<UiExhibition> = persistentListOf(),
    val contact: ImmutableList<UiContact> = persistentListOf(),
    val email: String = "",
)

data class UiExhibition(
    val title: String = "",
    val exhibitionDate: String = "",
)

data class UiContact(
    val type: String = "",
    val value: String = "",
)
