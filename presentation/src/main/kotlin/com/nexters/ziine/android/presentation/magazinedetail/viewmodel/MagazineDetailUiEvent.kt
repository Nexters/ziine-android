package com.nexters.ziine.android.presentation.magazinedetail.viewmodel

sealed interface MagazineDetailUiEvent {
    object BackToPrevious : MagazineDetailUiEvent
}
