package com.example.shopapp.di

import com.example.shopapp.common.database.data.database.BookmarkDatabase
import com.example.shopapp.common.database.data.database.BookmarkDao
import com.example.shopapp.features.bookmarksScreen.data.dataSource.local.LocalDataSource
import com.example.shopapp.features.bookmarksScreen.data.dataSource.local.LocalDataSourceImpl
import com.example.shopapp.features.bookmarksScreen.data.mapper.BookmarkScreenMapper
import com.example.shopapp.features.bookmarksScreen.data.repository.BookmarkRepositoryImpl
import com.example.shopapp.features.bookmarksScreen.domain.repository.BookmarkRepository
import com.example.shopapp.features.bookmarksScreen.domain.usecase.DeleteAllBookmarksUseCase
import com.example.shopapp.features.bookmarksScreen.domain.usecase.DeleteBookmarkUseCase
import com.example.shopapp.features.bookmarksScreen.domain.usecase.GetBookmarksListUseCase
import com.example.shopapp.features.bookmarksScreen.presentation.viewModel.BookmarksScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val bookmarkScreenDataModule = module {
    single<BookmarkRepository> {
        BookmarkRepositoryImpl(localDataSource = get(), bookmarkScreenMapper = get())
    }

    single<LocalDataSource> {
        LocalDataSourceImpl(bookmarkDao = get())
    }

    factory<BookmarkScreenMapper> {
        BookmarkScreenMapper()
    }

    single<BookmarkDao> {
        BookmarkDatabase.getDatabase(androidApplication()).bookmarkDao()
    }

}

val bookmarkScreenDomainModule = module {

    factory<GetBookmarksListUseCase> {
        GetBookmarksListUseCase(bookmarkRepository = get())
    }

    factory<DeleteBookmarkUseCase> {
        DeleteBookmarkUseCase(bookmarkRepository = get())
    }

    factory<DeleteAllBookmarksUseCase> {
        DeleteAllBookmarksUseCase(bookmarkRepository = get())
    }
}

val bookmarkScreenPresentationModule = module {

    viewModel {
        BookmarksScreenViewModel(
            get(),
            get(),
            get()
        )
    }
}