package com.gyurme.template.network

import com.gyurme.template.data.Transaction
import kotlinx.serialization.Serializable

@Serializable
data class TransactionListResponse(
    val transactions: List<Transaction>,
    val totalCount: Int
)
