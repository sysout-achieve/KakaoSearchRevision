package com.gunt.kakaosearchrevision.data.domain

import java.io.Serializable

// Domain 영역에 구성, 따로 만들어 비즈니스 로직을 수행할 클래스로 판단하여 따로 파일을 나눔
data class Book(
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
