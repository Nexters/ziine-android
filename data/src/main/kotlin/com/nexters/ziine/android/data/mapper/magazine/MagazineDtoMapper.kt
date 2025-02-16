package com.nexters.ziine.android.data.mapper.magazine

import com.nexters.ziine.android.data.httpClient.dto.response.MagazineResponse
import com.nexters.ziine.android.domain.entity.Magazine
import com.nexters.ziine.android.domain.entity.Magazines

fun MagazineResponse.toMagazines(): Magazines =
    Magazines(
        this.magazines.map {
            Magazine(
                id = it.id,
                title = it.title,
                subTitle = it.summary,
                keyWords = it.keywords,
                thumbnailUrl = it.thumbnailImageUrl,
                backgroundColor = it.backgroundColor,
            )
        },
    )
