package com.gunt.kakaosearchrevision.data.repository

import com.gunt.kakaosearchrevision.data.domain.Book

class FakeBookRepository constructor(private var bookList: MutableList<Book>) : BookRepository {

    override suspend fun searchBooks(keyword: String, page: Int): List<Book> {
        return bookList.filter { it.title.contains(keyword) }
    }
}
