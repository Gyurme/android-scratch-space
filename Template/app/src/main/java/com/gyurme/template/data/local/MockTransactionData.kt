package com.gyurme.template.data.local

import com.gyurme.template.data.Transaction
import com.gyurme.template.data.TransactionStatus

object MockTransactionData {
    val sampleTransactions = listOf(
        Transaction(
            id = 1L,
            amount = -45.67,
            merchant = "Starbucks",
            date = "2024-08-24T08:30:00Z",
            status = TransactionStatus.PENDING,
            category = "Food & Drink",
            description = "Coffee and pastry"
        ),
        Transaction(
            id = 2L,
            amount = -120.00,
            merchant = "Shell Gas Station",
            date = "2024-08-23T18:45:00Z",
            status = TransactionStatus.PENDING,
            category = "Transport",
            description = "Fuel"
        ),
        Transaction(
            id = 3L,
            amount = 2500.00,
            merchant = "Salary Deposit",
            date = "2024-08-22T09:00:00Z",
            status = TransactionStatus.APPROVED,
            category = "Income",
            description = "Monthly salary"
        ),
        Transaction(
            id = 4L,
            amount = -89.99,
            merchant = "Amazon",
            date = "2024-08-21T14:20:00Z",
            status = TransactionStatus.APPROVED,
            category = "Shopping",
            description = "Electronics"
        )
    )
}