package com.nexters.ziine.android.presentation.registerArtwork.register.viewmodel

import com.nexters.ziine.android.presentation.registerArtwork.pending.viewmodel.PendingUiEvent

interface RegisterUiEvent {
    object NavigateToComplete : RegisterUiEvent
    object BackToPrevious : RegisterUiEvent
}
