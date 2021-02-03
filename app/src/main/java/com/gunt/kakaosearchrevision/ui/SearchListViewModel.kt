package com.gunt.kakaosearchrevision.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.data.repository.BookRepository
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
                loading.value = true
                when (event) {
                    is BookListEvent.NewSearchEvent -> {
                        searchNew()
                        clearAdapter()
                        setDataToAdapter()
                    }
                    is BookListEvent.NextPageEvent -> {
                        searchNextPage()
                        setDataToAdapter()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loading.value = false
            }
        }
    }

    suspend fun searchNew() {
        resetList()
        search.firstPage()
        responseBook = getBooksRepository()
    }

    suspend fun searchNextPage() {
        incrementPage()
        val result = getBooksRepository()
        appendBooks(result)
    }

    private suspend fun getBooksRepository(): List<Book> {
        return bookRepository.searchBooks(keyword = search.searchStr, page = search.page)
    }

    fun appendBooks(books: List<Book>) {
        val current = ArrayList(this.responseBook)
        current.addAll(books)
        this.responseBook = current
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

    private fun setDataToAdapter() {
        booksListAdapter.setDataList(responseBook)
    }

    private fun clearAdapter() {
        booksListAdapter.clearData()
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
