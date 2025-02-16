package com.nexters.ziine.android.domain.repository

import com.nexters.ziine.android.domain.entity.Magazines

interface MagazineRepository {
    suspend fun fetchMagazines(): Result<Magazines>
}
