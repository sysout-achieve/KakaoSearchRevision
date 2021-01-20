package com.gunt.kakaosearchrevision.ui

import com.google.common.truth.Truth.assertThat
import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.data.domain.DummyBookData
import com.gunt.kakaosearchrevision.data.repository.FakeBookRepository
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Field
import java.lang.reflect.Method

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
        val responseBook:List<Book> = field.get(searchListViewModel) as List<Book>
        assertThat(responseBook).hasSize(0)
    }

    @Test
    fun incrementPageTest() {
        //given
        val tempPage = searchListViewModel.search.page
        val method: Method = searchListViewModel.javaClass.getDeclaredMethod("incrementPage")
        method.isAccessible = true

        //when
        method.invoke(searchListViewModel)

        //then
        assertThat(searchListViewModel.search.page).isEqualTo(tempPage+1)
    }

    @Test
    fun appendBookTest() {
        //given
        //DummyBookData.books 갯수 = 5
        var books :List<Book> = listOf()
        val current = ArrayList(books)
        current.addAll(DummyBookData.books)
        books = current
        assertThat(searchListViewModel.responseBook).hasSize(0)

        //when
        searchListViewModel.appendBooks(books)

        //then
        assertThat(searchListViewModel.responseBook).hasSize(5)
    }

}