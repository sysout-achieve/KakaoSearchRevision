package com.gunt.kakaosearchrevision.data.repository.network.response

import com.gunt.kakaosearchrevision.data.repository.network.model.BookDto

data class ResponseBook(
    val meta: BookMeta,
    val documents: List<BookDto>
)

data class BookMeta(
    val is_end: Boolean,
    val total_count: Int,
    val pageableCount: Int
)
