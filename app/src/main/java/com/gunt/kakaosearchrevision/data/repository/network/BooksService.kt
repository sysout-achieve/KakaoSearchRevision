package com.gunt.kakaosearchrevision.data.repository.network

import com.gunt.kakaosearchrevision.data.repository.network.response.ResponseBook
import com.gunt.kakaosearchrevision.di.KAKAO_API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val REQUEST_BOOK_LIST_SIZE_DEFAULT: Int = 50
const val REQUEST_BOOK_LIST_TYPE_DEFAULT: String = "accuracy"

interface BooksService {
    @GET("/v3/search/book")
    @Headers("Authorization: KakaoAK $KAKAO_API_KEY")
    suspend fun requestSearchBooks(
        @Query("query") keyword: String,
        @Query("sort") sort: String = REQUEST_BOOK_LIST_TYPE_DEFAULT,
        @Query("page") page: Int,
        @Query("size") size: Int = REQUEST_BOOK_LIST_SIZE_DEFAULT
    ): ResponseBook
}
