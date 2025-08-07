package com.gyurme.bookshelf.di

import com.gyurme.bookshelf.data.BookShelfRepository
import com.gyurme.bookshelf.data.NetworkBookShelfRepository
import com.gyurme.bookshelf.network.BookshelfApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideBaseUrl(): String {
        return "https://www.googleapis.com/books/v1/"
    }

    @Provides
    fun provideRetrofit(baseUrl: String) : Retrofit {
        val networkJson = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideBookshelfApiService(retrofit: Retrofit): BookshelfApiService =
        retrofit.create(BookshelfApiService::class.java)

    @Provides
    fun provideBookshelfRepository(bookshelfApiService: BookshelfApiService): BookShelfRepository =
        NetworkBookShelfRepository(bookshelfApiService)
}