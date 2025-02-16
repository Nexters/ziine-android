package com.nexters.ziine.android.data.di

import com.nexters.ziine.android.data.repository.DefaultArtworkRepository
import com.nexters.ziine.android.data.repository.DefaultMagazineRepository
import com.nexters.ziine.android.domain.repository.ArtworkRepository
import com.nexters.ziine.android.domain.repository.MagazineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindArtworkRepository(repository: DefaultArtworkRepository): ArtworkRepository

    @Binds
    @Singleton
    abstract fun bindMagazineRepository(repository: DefaultMagazineRepository): MagazineRepository
}
