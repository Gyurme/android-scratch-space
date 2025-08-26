package com.gyurme.template.data

import com.gyurme.template.data.local.MockTransactionData
import kotlinx.coroutines.delay

class LocalTransactionRepository : TransactionRepository {
    override suspend fun getTransactions(): Result<List<Transaction>> {
        return try {
            delay(1000) // Simulate network
            Result.success(MockTransactionData.sampleTransactions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun approveTransaction(id: Long): Result<List<Transaction>> {
        return try {
            delay(500) // Simulate network
            val updated = MockTransactionData.sampleTransactions
                .find { it.id == id }
                ?.copy(status = TransactionStatus.APPROVED)

            updated?.let { Result.success(listOf(it)) }
                ?: Result.failure(Exception("Transaction not found"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}