package com.gyurme.template.di

import com.gyurme.template.data.TransactionRepository
import com.gyurme.template.network.TransactionApiService
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class AppModule {
    @Provides
    fun provideBaseUrl(): String {
        return "https://my.api.mockaroo.com/"
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
    fun provideTransactionApiService(retrofit: Retrofit): TransactionApiService =
        retrofit.create(TransactionApiService::class.java)

    @Provides
    fun provideTransactionRepository(transactionApiService: TransactionApiService): TransactionRepository =
        NetworkTransactionRepository(transactionApiService)
}