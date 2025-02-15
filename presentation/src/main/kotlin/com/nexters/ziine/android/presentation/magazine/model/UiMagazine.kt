package com.nexters.ziine.android.presentation.magazine.model

import kotlinx.collections.immutable.ImmutableList

data class UiMagazine(
    val magazineId: Int,
    val title: String,
    val subTitle: String,
    val keyWords: ImmutableList<String>,
    val thumbnailUrl: String,
    val backgroundColor: String
)
