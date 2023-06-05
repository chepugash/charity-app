package com.example.sign.data.api.entity

import com.google.firebase.Timestamp

data class DbTransactionEntity(
    val date: Timestamp,
    val foundationId: Long,
    val foundationName: String,
    val sum: Long,
)