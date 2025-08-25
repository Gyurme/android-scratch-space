package com.gyurme.template.data

interface TransactionRepository {
    suspend fun getTransactions(): Result<List<Transaction>>
    suspend fun approveTransaction(id: Long): Result<Transaction>
    suspend fun declineTransaction(id: Long): Result<Transaction>
}