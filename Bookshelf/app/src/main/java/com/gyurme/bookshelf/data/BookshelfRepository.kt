package com.gyurme.bookshelf.data

import com.gyurme.bookshelf.network.BookshelfApiService
import com.gyurme.bookshelf.network.BookDto
import com.gyurme.bookshelf.network.BookDetails
import javax.inject.Inject

interface BookShelfRepository {
    suspend fun getBooksForCategory(category: String): List<BookDto>
    suspend fun getBookDetails(bookId: String): BookDetails
}

class NetworkBookShelfRepository @Inject constructor(private val bookShelfApiService: BookshelfApiService) :
    BookShelfRepository {
    override suspend fun getBooksForCategory(category: String): List<BookDto> {
        val result = bookShelfApiService.getBooks(category)
        return result.items
    }

    override suspend fun getBookDetails(bookId: String) = bookShelfApiService.getBookDetails(bookId).volumeInfo
}
