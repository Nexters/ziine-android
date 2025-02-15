package com.nexters.ziine.android.presentation.mapper.magazine

import com.nexters.ziine.android.domain.entity.Magazines
import com.nexters.ziine.android.presentation.magazine.model.UiMagazine
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

fun Magazines.toUiMagazines(): ImmutableList<UiMagazine> =
    this.value.map {
        UiMagazine(
            magazineId = it.id,
            title = it.title,
            subTitle = it.subTitle,
            keyWords = it.keyWords.toImmutableList(),
            thumbnailUrl = it.thumbnailUrl,
            backgroundColor = it.backgroundColor,
        )
    }.toImmutableList()

