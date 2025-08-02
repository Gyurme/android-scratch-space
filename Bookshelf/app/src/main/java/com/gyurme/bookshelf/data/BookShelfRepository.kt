package com.gyurme.bookshelf.data

import com.gyurme.bookshelf.network.BookShelfApiService
import com.gyurme.bookshelf.network.Volume
import javax.inject.Inject

interface BookShelfRepository {
    suspend fun getBooksForCategory(category: String): List<Volume>
}

class NetworkBookShelfRepository @Inject constructor(private val bookShelfApiService: BookShelfApiService) :
    BookShelfRepository {
    override suspend fun getBooksForCategory(category: String): List<Volume> {
        val result = bookShelfApiService.getVolumes(category)
        return result.items
    }
}