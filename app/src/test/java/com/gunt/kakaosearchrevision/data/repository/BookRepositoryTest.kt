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
        bookRepository = FakeBookRepository()
    }


    @Test
    fun searchBooksTest() {
        //DummyBookData 에 title 이 포함된 Book 을 3개 가지고 있음
        //given
        search.searchStr = "title"
        //when
        var list: List<Book>
        runBlocking {
            list = bookRepository.searchBooks(search.searchStr, search.page)
        }
        //then
        assertThat(list).hasSize(3)
    }
}