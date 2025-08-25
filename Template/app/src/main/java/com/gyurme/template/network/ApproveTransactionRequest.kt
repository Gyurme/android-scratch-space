package com.gyurme.template.network

import kotlinx.serialization.Serializable

@Serializable
data class ApproveTransactionRequest(
    val transactionId: Long,
    val approved: Boolean
)