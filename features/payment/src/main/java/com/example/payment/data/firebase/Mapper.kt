package com.example.payment.data.firebase

import com.example.payment.domain.entity.TransactionEntity
import com.example.payment.domain.entity.UserEntity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUserEntity(): UserEntity = UserEntity(id = uid)

fun TransactionEntity.toFirebaseTransactionEntity(): FirebaseTransactionEntity =
    FirebaseTransactionEntity(
        date = Timestamp(date),
        foundationId = foundationId,
        foundationName = foundationName,
        sum = sum
    )