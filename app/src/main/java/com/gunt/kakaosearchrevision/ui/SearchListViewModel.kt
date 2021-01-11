package com.gunt.kakaosearchrevision.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunt.kakaosearchrevision.repository.api.BooksApiService
import com.gunt.kakaosearchrevision.repository.api.RetrofitApi
import com.gunt.kakaosearchrevision.data.ResultBook
import com.gunt.kakaosearchrevision.ui.recyclerview.BooksListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Exception

const val REQUEST_BOOK_LIST_SIZE: Int = 50

class SearchListViewModel : ViewModel() {
    var booksListAdapter = BooksListAdapter()

    var searchTxtStr = ""
    private var resultBook: MutableLiveData<ResultBook?> = MutableLiveData()

    private var currentPage = 0
    private var is_end = false


    private fun callApiBooks(){
        val retrofit = RetrofitApi.getRetrofitInstance().create(BooksApiService::class.java)
        retrofit.requestSearchBook(keyword = searchTxtStr, page = ++currentPage, size = REQUEST_BOOK_LIST_SIZE)
                .enqueue(object : Callback<ResultBook> {
                    override fun onFailure(call: Call<ResultBook>, t: Throwable) {
                        clearCurrentResultList()
                    }

                    override fun onResponse(call: Call<ResultBook>, response: Response<ResultBook>) {
                        if (response.isSuccessful) {
                            //만약 null일 경우 is_end변수를 true로 만들어서 현재 검색한 내용의 endlessScroll(다음 페이지검색) 기능 막음
                            is_end = response.body()?.isEnd() ?: true
                            resultBook.postValue(response.body())
                        } else {
                            clearCurrentResultList()
                        }
                    }
                })
    }

    fun callSearchBooksList() {
        try {
            emptyText()
            firstPage()
            callApiBooks()
        } catch (e: Exception) {
            Log.e("kakao eLog", e.message.toString())
        }
    }

    fun callMoreSearchBooksList() {
        if (is_end) return
        if (booksListAdapter.itemCount < 10) return
        try {
            emptyText()
            callApiBooks()
        } catch (e: Exception) {
            Log.e("kakao eLog", e.message.toString())
        }
    }

    private fun firstPage() {
        currentPage = 0
        clearCurrentResultList()
    }

    private fun emptyText() {
        if (searchTxtStr == "") {
            is_end = true
            clearCurrentResultList()
            throw Exception("Empty Text")
        }
    }

    fun getBooksDataObserver(): MutableLiveData<ResultBook?> {
        return resultBook
    }

    fun setBooksDataAdapter(data: ResultBook?) {
        booksListAdapter.setDataList(data)
    }

    fun getAdapter(): BooksListAdapter {
        return booksListAdapter
    }

    private fun clearCurrentResultList() {
        booksListAdapter.dataListClear()
        postEmptyValue()
    }

    private fun postEmptyValue() {
        resultBook.postValue(null)
    }

}
