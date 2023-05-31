package com.example.payment.domain.repository

import com.example.payment.domain.entity.TransactionEntity
import com.example.payment.domain.entity.UserEntity
import com.google.android.gms.tasks.Task

interface FirebaseRepository {

    suspend fun getUser(): UserEntity?

    suspend fun createHistoryDocument(): Task<Void>

    suspend fun addToHistory(transactionEntity: TransactionEntity): Task<Void>
}