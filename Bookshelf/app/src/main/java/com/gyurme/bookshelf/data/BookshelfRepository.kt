package com.gyurme.bookshelf.data

import com.gyurme.bookshelf.network.BookshelfApiService
import com.gyurme.bookshelf.network.Book
import javax.inject.Inject

interface BookShelfRepository {
    suspend fun getBooksForCategory(category: String): List<Book>
}

class NetworkBookShelfRepository @Inject constructor(private val bookShelfApiService: BookshelfApiService) :
    BookShelfRepository {
    override suspend fun getBooksForCategory(category: String): List<Book> {
        val result = bookShelfApiService.getVolumes(category)
        return result.items
    }
}