package com.gunt.kakaosearchrevision.data.repository.api.response

import com.gunt.kakaosearchrevision.data.repository.api.model.BookDto

data class ResponseBook(
        val meta: BookMeta,
        val documents: List<BookDto>
) {
    fun isEmpty() = documents.isEmpty()
    fun isEnd() = meta.is_end
}

data class BookMeta(
        val is_end: Boolean,
        val total_count: Int,
        val pageableCount: Int,
)

