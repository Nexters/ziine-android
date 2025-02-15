package com.nexters.ziine.android.data.repository

import com.nexters.ziine.android.data.httpClient.convertClassifiedResult
import com.nexters.ziine.android.data.mapper.magazine.toMagazines
import com.nexters.ziine.android.data.service.ZiineService
import com.nexters.ziine.android.domain.entity.Magazines
import com.nexters.ziine.android.domain.repository.MagazineRepository
import javax.inject.Inject

class DefaultMagazineRepository @Inject constructor(
    private val service: ZiineService,
) : MagazineRepository {
    override suspend fun fetchMagazines(): Result<Magazines> =
        convertClassifiedResult {
            service.fetchMagazines().toMagazines()
        }
}
