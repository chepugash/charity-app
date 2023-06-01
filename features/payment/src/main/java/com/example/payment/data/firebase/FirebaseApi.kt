package com.example.payment.data.firebase

import com.example.payment.domain.entity.TransactionEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

interface FirebaseApi {

    suspend fun getUser(): FirebaseUser?

    suspend fun createHistoryDocument(): Task<Void>

    suspend fun addToHistory(firebaseTransactionEntity: FirebaseTransactionEntity): Task<Void>
}