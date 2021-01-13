package com.gunt.kakaosearchrevision.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gunt.kakaosearchrevision.domain.data.Book
import com.gunt.kakaosearchrevision.repository.BookRepository
import com.gunt.kakaosearchrevision.ui.recyclerview.BooksListAdapter
import kotlinx.coroutines.launch
import kotlin.Exception

class SearchListViewModel
@ViewModelInject
constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    var booksListAdapter = BooksListAdapter()

    var searchTxtStr = ""
    var currentPage = 1
    private var responseBook: List<Book> = listOf()

    private var is_end = false
    private var loading = MutableLiveData(false)

    fun onTriggerEvent(event: BookListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is BookListEvent.NewSearchEvent -> {
                        newSearch()
                    }
                    is BookListEvent.NextPageEvent -> {
                        nextPage()
                    }
                    is BookListEvent.RestoreStateEvent -> {
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun newSearch() {
        try {
            loading.value = true
            resetSearchState()
            val result = bookRepository.searchBooks(keyword = searchTxtStr, page = currentPage)
            responseBook = result
            booksListAdapter.clearData()
            booksListAdapter.setDataList(responseBook)
        } catch (e: Exception) {

        }
        loading.value = false
    }

    private suspend fun nextPage() {
        loading.value = true
        incrementPage()

        val result = bookRepository.searchBooks(keyword = searchTxtStr, page = currentPage)
        appendBooks(result)
        loading.value = false
    }

    private fun appendBooks(books: List<Book>) {
        val current = ArrayList(this.responseBook)
        current.addAll(books)
        this.responseBook = current
        booksListAdapter.setDataList(current)
    }

    private fun incrementPage() {
        setPage(currentPage + 1)
    }

    private fun resetSearchState() {
        is_end = false
        responseBook = listOf()
        setPage(1)
    }

    private fun setPage(page: Int) {
        this.currentPage = page
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }

    fun getAdapter(): BooksListAdapter {
        return booksListAdapter
    }

}
