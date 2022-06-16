package com.example.shopapp.features.mainScreen.data.source.local

import com.example.shopapp.common.database.data.database.BookmarkDao
import com.example.shopapp.common.database.data.modelDB.BookmarkDB


class LocalDataSourceImpl(private val bookmarkDao: BookmarkDao) : LocalDataSource {

    override suspend fun addBookmark(bookmarkDB: BookmarkDB) {
        bookmarkDao.addBookmark(bookmarkDB)
    }

    override suspend fun deleteBookmark(bookmarkDB: BookmarkDB) {
        bookmarkDao.deleteBookmark(bookmarkDB)
    }


}