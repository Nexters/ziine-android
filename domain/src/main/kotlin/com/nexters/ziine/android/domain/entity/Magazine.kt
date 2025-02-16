package com.nexters.ziine.android.domain.entity

data class Magazine(
    val id: Int,
    val title: String,
    val subTitle: String,
    val keyWords: List<String>,
    val thumbnailUrl: String,
    val backgroundColor: String
)
