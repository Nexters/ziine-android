package com.nexters.ziine.android.presentation.artworkdetail.viewmodel

sealed interface ArtworkDetailUiEvent {
    data object NavigateBack : ArtworkDetailUiEvent

    data class ShareUrl(val url: String) : ArtworkDetailUiEvent

    data class CopyValue(val type: String, val value: String) : ArtworkDetailUiEvent
}
