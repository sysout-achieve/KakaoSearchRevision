package com.gunt.kakaosearchrevision.repository

import com.gunt.kakaosearchrevision.domain.data.Book

interface BookRepository {

    suspend fun searchBooks( keyword: String, page: Int ): List<Book>

}