package com.gunt.kakaosearchrevision.data.repository

import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.data.domain.DummyBookData


class FakeBookRepository : BookRepository {

    var bookList = mutableListOf<Book>()

    override suspend fun searchBooks(keyword: String, page: Int): List<Book> {
        bookList.addAll(DummyBookData.books.filter {
            it.title.contains(keyword)
        })
        return bookList
    }

}