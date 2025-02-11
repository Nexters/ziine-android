package com.nexters.ziine.android.data.di

import com.nexters.ziine.android.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun providesAdjustMaxRequestPerHostOKHttpDispatcher(): Dispatcher =
        Dispatcher().apply {
            maxRequestsPerHost = 10
        }

    @Provides
    fun providesOKHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        okHttpDispatcher: Dispatcher,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .dispatcher(okHttpDispatcher)
            .build()

    @Provides
    @Singleton
    fun providesZiineRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        /** baseurl 추후 추가 예정 */
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }
}
