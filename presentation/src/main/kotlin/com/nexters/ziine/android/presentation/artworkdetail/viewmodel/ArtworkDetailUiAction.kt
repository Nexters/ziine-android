package com.nexters.ziine.android.presentation.artworkdetail.viewmodel

sealed interface ArtworkDetailUiAction {
    data object OnBackClick : ArtworkDetailUiAction

    data class OnShareClick(val url: String) : ArtworkDetailUiAction

    data class OnCopyClick(
        val type: String,
        val value: String,
    ) : ArtworkDetailUiAction
}
