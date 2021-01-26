package com.gunt.kakaosearchrevision.di

import com.gunt.kakaosearchrevision.BuildConfig
import com.gunt.kakaosearchrevision.data.repository.network.BooksService
import com.gunt.kakaosearchrevision.data.repository.network.model.BookDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "https://dapi.kakao.com"

const val KAKAO_API_KEY = BuildConfig.KAKAO_KEY

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideBookMapper(): BookDtoMapper {
        return BookDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRetrofitApiService(): BooksService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksService::class.java)
    }

}
