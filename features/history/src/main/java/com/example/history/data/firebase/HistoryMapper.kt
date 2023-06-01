package com.example.history.data.firebase

import com.example.history.domain.entity.TransactionEntity
import com.google.firebase.Timestamp

fun FirebaseTransactionEntity.toTransactionEntity(): TransactionEntity =
    TransactionEntity(
        date = date.toDate(),
        foundationName = foundationName,
        sum = sum
    )