package com.gunt.kakaosearchrevision.data.repository

import com.google.common.truth.Truth.assertThat
import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.ui.SearchListViewModel
import kotlinx.coroutines.runBlocking

import org.junit.Before

import org.junit.Test

class BookRepositoryTest {

    lateinit var bookRepository: BookRepository

    var search = SearchListViewModel.Search()

    @Before
    fun setUp() {
        val books: List<Book> = listOf(
                Book(title = "title1"),
                Book(title = "title2"),
                Book(title = "title3"),
                Book(title = "empty1"),
                Book(title = "empty2")
        )
        bookRepository = FakeBookRepository(bookList = books as MutableList<Book>)
    }


    @Test
    fun searchBooksTest() = runBlocking {
        //given
        search.searchStr = "title"

        //when
        val list = bookRepository.searchBooks(search.searchStr, search.page)

        //then
        assertThat(list).hasSize(3)
    }

}