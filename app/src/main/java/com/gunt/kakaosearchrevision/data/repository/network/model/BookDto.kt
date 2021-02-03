package com.gunt.kakaosearchrevision.data.repository.network.model

import java.io.Serializable

data class BookDto(
    val authors: ArrayList<String> = ArrayList(),
    val contents: String = "",
    val datetime: String = "",
    val isbn: String = "",
    val price: Long = 0,
    val publisher: String = "",
    val sale_price: Long = 0,
    val status: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val translators: ArrayList<String> = ArrayList(),
    val url: String = ""
) : Serializable
