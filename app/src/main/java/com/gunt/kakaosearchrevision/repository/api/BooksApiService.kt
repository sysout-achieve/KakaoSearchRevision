package com.gunt.kakaosearchrevision.api

import com.gunt.kakaosearchrevision.data.ResultBook
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface  BooksApiService {
    @Headers("Authorization: KakaoAK $KAKAO_API_KEY")
    @GET("/v3/search/book")
    fun requestSearchBook(
        @Query("query") keyword: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int,
        @Query("size") size: Int = 50
    ): Call<ResultBook>


}