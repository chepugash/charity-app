package com.example.history.domain.entity

import java.util.Date

data class TransactionEntity(
    val date: Date,
    val foundationName: String,
    val sum: Long,
)