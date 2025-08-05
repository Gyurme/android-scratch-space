package com.gyurme.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("volumes")
    suspend fun getVolumes(@Query("q") query: String) : BookResults
}