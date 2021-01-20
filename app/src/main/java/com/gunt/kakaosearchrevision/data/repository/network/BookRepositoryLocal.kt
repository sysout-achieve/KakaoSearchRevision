package com.gunt.kakaosearchrevision.data.repository.network

import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.data.repository.BookRepository
import com.gunt.kakaosearchrevision.data.repository.network.model.BookDtoMapper
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class BookRepositoryLocal
constructor(
    private var booksService: BooksService,
    private var mapper: BookDtoMapper
) : BookRepository {

    override suspend fun searchBooks(keyword: String, page: Int): List<Book> {
        return mapper.toDomainModelList(
            booksService.requestSearchBooks(
                keyword = keyword,
                page = page
            ).documents
        )
    }

}