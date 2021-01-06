package com.gunt.kakaosearchrevision.api

import com.gunt.kakaosearchrevision.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dapi.kakao.com"
const val KAKAO_API_KEY = BuildConfig.KAKAO_KEY

object RetrofitApi {
    fun getRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}