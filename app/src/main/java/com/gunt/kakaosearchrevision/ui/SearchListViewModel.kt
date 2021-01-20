package com.gunt.kakaosearchrevision.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gunt.kakaosearchrevision.data.repository.BookRepository
import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.ui.recyclerview.BooksListAdapter
import kotlinx.coroutines.launch
import kotlin.Exception

class SearchListViewModel
@ViewModelInject
constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    var booksListAdapter = BooksListAdapter()
    var search = Search()
    var responseBook: List<Book> = listOf()
    private var loading = MutableLiveData(false)

    fun onTriggerEvent(event: BookListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is BookListEvent.NewSearchEvent -> {
                        searchNew()
                    }
                    is BookListEvent.NextPageEvent -> {
                        searchNextPage()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun searchNew() {
        loading.value = true
        resetList()
        search.firstPage()
        val result = getBooksRepository()
        responseBook = result
        booksListAdapter.clearData()
        booksListAdapter.setDataList(responseBook)
        loading.value = false
    }

    private suspend fun searchNextPage() {
        loading.value = true
        incrementPage()
        val result = getBooksRepository()
        booksListAdapter.setDataList(appendBooks(result))
        loading.value = false
    }

    private suspend fun getBooksRepository(): List<Book> {
        return bookRepository.searchBooks(keyword = search.searchStr, page = search.page)
    }

    fun appendBooks(books: List<Book>) :List<Book> {
        val current = ArrayList(this.responseBook)
        current.addAll(books)
        this.responseBook = current
        return this.responseBook
    }

    private fun incrementPage() {
        search.incrementPage()
    }

    private fun resetList() {
        responseBook = listOf()
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }

    fun getAdapter(): BooksListAdapter {
        return booksListAdapter
    }

    class Search {
        var searchStr = ""
        var page = 1

        fun incrementPage() {
            page++
        }

        fun firstPage() {
            page = 1
        }

        fun restoreState() {
            searchStr = ""
            page = 1
        }
    }
}
