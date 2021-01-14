package com.gunt.kakaosearchrevision.data.repository.api

import com.gunt.kakaosearchrevision.di.KAKAO_API_KEY
import com.gunt.kakaosearchrevision.data.repository.REQUEST_BOOK_LIST_SIZE
import com.gunt.kakaosearchrevision.data.repository.api.response.ResponseBook
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BooksService {
    @GET("/v3/search/book")
    @Headers("Authorization: KakaoAK $KAKAO_API_KEY")
    suspend fun requestSearchBooks(
            @Query("query") keyword: String,
            @Query("sort") sort: String = "accuracy",
            @Query("page") page: Int,
            @Query("size") size: Int = REQUEST_BOOK_LIST_SIZE
    ): ResponseBook

}