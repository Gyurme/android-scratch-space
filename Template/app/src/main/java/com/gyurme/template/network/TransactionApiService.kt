package com.gyurme.template.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionApiService {

    @Headers("X-API-Key: ee1ede00")
    @GET("transactions")
    suspend fun getTransactions(): TransactionListResponse

    @Headers("X-API-Key: ee1ede00")
    @POST("transactions/{transactionId")
    suspend fun approveTransaction(@Path("transactionId") transactionId: Long)
            : ApproveTransactionResponse
}