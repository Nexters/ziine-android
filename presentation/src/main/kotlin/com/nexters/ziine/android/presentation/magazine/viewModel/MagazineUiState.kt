package com.nexters.ziine.android.presentation.magazine.viewModel

import com.nexters.ziine.android.presentation.magazine.model.UiMagazine
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MagazineUiState(
    val isLoading: Boolean = false,
    val magazines: ImmutableList<UiMagazine> = persistentListOf(),
)
