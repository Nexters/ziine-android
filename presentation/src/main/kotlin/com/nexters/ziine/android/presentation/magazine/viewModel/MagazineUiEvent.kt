package com.nexters.ziine.android.presentation.magazine.viewModel

sealed interface MagazineUiEvent {
    data class MoveToMagazineDetail(val magazineId: Int) : MagazineUiEvent
}
