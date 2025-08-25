package com.gyurme.template.data

import com.gyurme.template.data.local.MockTransactionData
import com.gyurme.template.network.TransactionApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class NetworkTransactionRepository @Inject constructor(private val transactionApiService: TransactionApiService) :
    TransactionRepository {
    override suspend fun getTransactions(): Result<List<Transaction>> {
        return try {
            Result.success(transactionApiService.getTransactions())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun approveTransaction(id: Long): Result<Transaction> {
        return try {
            Result.success(transactionApiService.approveTransaction(id).transaction)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}