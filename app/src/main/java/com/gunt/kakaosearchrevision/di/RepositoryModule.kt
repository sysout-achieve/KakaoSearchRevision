package com.gunt.kakaosearchrevision.di

import com.gunt.kakaosearchrevision.data.repository.BookRepository
import com.gunt.kakaosearchrevision.data.repository.network.BookRepositoryRemote
import com.gunt.kakaosearchrevision.data.repository.network.BooksService
import com.gunt.kakaosearchrevision.data.repository.network.model.BookDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBookRepository(
        booksService: BooksService,
        bookDtoMapper: BookDtoMapper
    ): BookRepository {
        return BookRepositoryRemote(
            booksService = booksService,
            mapper = bookDtoMapper
        )
    }
}
