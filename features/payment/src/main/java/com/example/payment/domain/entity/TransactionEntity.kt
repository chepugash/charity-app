package com.example.payment.domain.entity

import java.util.Date

data class TransactionEntity(
    val date: Date,
    val foundationId: Long,
    val foundationName: String,
    val sum: Long,
)