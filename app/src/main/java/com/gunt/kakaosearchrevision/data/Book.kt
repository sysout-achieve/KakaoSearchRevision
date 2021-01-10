package com.gunt.kakaosearchrevision.data


//Entity class로 만들어져야 할 클래스로 판단하여 따로 파일을 나눔
data class Book(
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
)

