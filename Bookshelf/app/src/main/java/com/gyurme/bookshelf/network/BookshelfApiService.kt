package com.gyurme.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Path

interface BookshelfApiService {
    @GET("volumes?q={query}")
    suspend fun getVolumes(@Path("query") query: String) : VolumeResults
}