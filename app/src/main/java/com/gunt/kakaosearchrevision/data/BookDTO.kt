package com.gunt.kakaosearchrevision.data

import java.io.Serializable

data class BookDTO(
        val authors: ArrayList<String>,
        val contents: String,
        val datetime: String,
        val isbn: String,
        val price: Long,
        val publisher: String,
        val sale_price: Long,
        val status: String,
        val thumbnail: String,
        val title: String,
        val translators: ArrayList<String>,
        val url: String
) : Serializable {
    fun toEntity(): Book {
        return Book(
                authors,
                contents,
                datetime,
                isbn,
                price,
                publisher,
                sale_price,
                status,
                thumbnail,
                title,
                translators,
                url
        )
    }
}

data class BookMeta(
        val is_end: Boolean,
        val total_count: Int,
        val pageableCount: Int,
)

data class ResultBook(
        val meta: BookMeta,
        val documents: ArrayList<BookDTO>
) {
    fun isEmpty() = documents.isEmpty()
    fun isEnd() = meta.is_end
}