package com.gunt.kakaosearchrevision.data.repository.api.model

import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.data.repository.DomainMapper

class BookDtoMapper : DomainMapper<BookDto, Book> {
    override fun mapToDomainModel(model: BookDto): Book {
        return Book(
                authors = model.authors,
                contents = model.contents,
                datetime = model.datetime,
                isbn = model.isbn,
                price = model.price,
                publisher = model.publisher,
                sale_price = model.sale_price,
                status = model.status,
                thumbnail = model.thumbnail,
                title = model.title,
                translators = model.translators,
                url = model.url
        )
    }

    override fun mapFromDomainModel(domainModel: Book): BookDto {
        return BookDto(
                authors = domainModel.authors,
                contents = domainModel.contents,
                datetime = domainModel.datetime,
                isbn = domainModel.isbn,
                price = domainModel.price,
                publisher = domainModel.publisher,
                sale_price = domainModel.sale_price,
                status = domainModel.status,
                thumbnail = domainModel.thumbnail,
                title = domainModel.title,
                translators = domainModel.translators,
                url = domainModel.url
        )
    }

    fun toDomainModelList(initial: List<BookDto>): List<Book>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainModelList(initial: List<Book>): List<BookDto>{
        return initial.map { mapFromDomainModel(it) }
    }

}