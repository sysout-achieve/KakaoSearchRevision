package com.gunt.kakaosearchrevision.data.repository

import com.gunt.kakaosearchrevision.data.domain.Book

interface BookRepository {

    suspend fun searchBooks( keyword: String, page: Int ): List<Book>

}