package com.example.payment.data.firebase

import com.google.firebase.Timestamp

data class FirebaseTransactionEntity(
    val date: Timestamp,
    val foundationId: Long,
    val foundationName: String,
    val sum: Long,
)