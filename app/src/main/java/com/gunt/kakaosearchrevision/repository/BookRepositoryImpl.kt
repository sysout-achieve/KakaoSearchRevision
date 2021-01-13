package com.gunt.kakaosearchrevision.repository

import com.gunt.kakaosearchrevision.domain.data.Book
import com.gunt.kakaosearchrevision.repository.api.BooksService
import com.gunt.kakaosearchrevision.repository.api.model.BookDtoMapper
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

const val REQUEST_BOOK_LIST_SIZE: Int = 50

@Module
@InstallIn(ActivityRetainedComponent::class)
class BookRepositoryImpl
constructor(
    private var booksService: BooksService,
    private var mapper: BookDtoMapper
) : BookRepository {

    override suspend fun searchBooks(keyword: String, page: Int): List<Book> {
        return mapper.toDomainModelList(
            booksService.requestSearchBooks(
                keyword = keyword,
                page = page,
                size = REQUEST_BOOK_LIST_SIZE
            ).documents
        )
    }

}