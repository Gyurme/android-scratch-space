package com.gyurme.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String) : BookResults

    @GET("volumes/{bookId}")
    suspend fun getBookDetails(@Path("bookId") bookId: String): BookDto
}