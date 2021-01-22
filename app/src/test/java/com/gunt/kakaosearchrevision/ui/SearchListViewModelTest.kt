package com.gunt.kakaosearchrevision.ui

import com.google.common.truth.Truth.assertThat
import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.data.domain.DummyBookData
import com.gunt.kakaosearchrevision.data.repository.FakeBookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.coroutines.CoroutineContext

class SearchListViewModelTest {
    private lateinit var searchListViewModel: SearchListViewModel

    @Before
    fun setUp() {
        searchListViewModel = SearchListViewModel(FakeBookRepository())
    }

    @Test
    fun initViewModelTest() {
        assertThat(searchListViewModel.search.searchStr).isEqualTo("")
        assertThat(searchListViewModel.search.page).isEqualTo(1)

        val field: Field =
            searchListViewModel.javaClass.getDeclaredField("responseBook")
        field.isAccessible = true
        val responseBook: List<Book> = field.get(searchListViewModel) as List<Book>
        assertThat(responseBook).hasSize(0)
    }

    @Test
    fun incrementPageTest() {
        //given
        val currentPage = searchListViewModel.search.page
        val method: Method = searchListViewModel.javaClass.getDeclaredMethod("incrementPage")
        method.isAccessible = true

        //when
        method.invoke(searchListViewModel)

        //then
        assertThat(searchListViewModel.search.page).isEqualTo(currentPage + 1)
    }

    @Test
    fun appendBookTest() {
        //given
        //DummyBookData.books 갯수 = 5
        var books: List<Book> = listOf()
        val current = ArrayList(books)
        current.addAll(DummyBookData.books)
        books = current
        assertThat(searchListViewModel.responseBook).hasSize(0)

        //when
        searchListViewModel.appendBooks(books)

        //then
        assertThat(searchListViewModel.responseBook).hasSize(5)
    }

    @Test
    fun searchNewTest() = runBlockingTest {
        //given
        searchListViewModel.search.searchStr = "title"

        //when
        searchListViewModel.searchNew()

        //then
        assertThat(searchListViewModel.search.page).isEqualTo(1)
        assertThat(searchListViewModel.responseBook.size).isEqualTo(3)
    }

    @Test
    fun searchNextPageTest() = runBlockingTest {
        //given
        searchListViewModel.search.searchStr = "title"
        val expectedPage = searchListViewModel.search.page + 1
        searchListViewModel.responseBook = DummyBookData.books.toList()

        //when
        searchListViewModel.searchNextPage()

        //then
        assertThat(searchListViewModel.search.page).isEqualTo(expectedPage)
        assertThat(searchListViewModel.responseBook.size).isNotEqualTo(DummyBookData.books.size)
        assertThat(searchListViewModel.responseBook.size).isNotEqualTo(0)
    }
}