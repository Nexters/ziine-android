package com.nexters.ziine.android.presentation.registerArtwork.pending.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString

data class UiPendingGuideItem(
    val title: String,
    val content: AnnotatedString,
    @DrawableRes val imageId: Int
)
