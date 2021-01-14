package com.gunt.kakaosearchrevision.di

import com.gunt.kakaosearchrevision.data.repository.BookRepository
import com.gunt.kakaosearchrevision.data.repository.BookRepositoryImpl
import com.gunt.kakaosearchrevision.data.repository.api.BooksService
import com.gunt.kakaosearchrevision.data.repository.api.model.BookDtoMapper
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
        return BookRepositoryImpl(
                booksService = booksService,
                mapper = bookDtoMapper
        )
    }
}