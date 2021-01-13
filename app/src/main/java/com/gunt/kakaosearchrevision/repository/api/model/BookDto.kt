package com.gunt.kakaosearchrevision.repository.api.model

import java.io.Serializable

data class BookDto(
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
) : Serializable

