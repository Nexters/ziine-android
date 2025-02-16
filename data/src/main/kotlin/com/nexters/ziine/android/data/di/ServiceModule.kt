package com.nexters.ziine.android.data.di

import com.nexters.ziine.android.data.service.ZiineService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    internal fun provideZiineService(retrofit: Retrofit): ZiineService = retrofit.create()
}
