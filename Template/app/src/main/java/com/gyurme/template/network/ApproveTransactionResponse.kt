package com.gyurme.template.network

import com.gyurme.template.data.Transaction
import kotlinx.serialization.Serializable

@Serializable
data class ApproveTransactionResponse(
    val success: Boolean,
    val transaction: Transaction,
    val message: String
)
