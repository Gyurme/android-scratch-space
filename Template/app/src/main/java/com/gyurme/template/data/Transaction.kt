package com.gyurme.template.data

import kotlinx.serialization.Serializable

@Serializable
enum class TransactionStatus {
    PENDING,
    APPROVED,
    DECLINED
}

@Serializable
data class Transaction(
    val id: Long,
    val amount: Double,
    val merchant: String,
    val date: String, // ISO format: "2024-08-24T10:30:00Z"
    val status: TransactionStatus,
    val category: String,
    val description: String? = null
)