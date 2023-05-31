package com.example.payment.data

import com.example.payment.data.firebase.FirebaseApi
import com.example.payment.data.firebase.toUserEntity
import com.example.payment.domain.entity.TransactionEntity
import com.example.payment.domain.entity.UserEntity
import com.example.payment.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseApi: FirebaseApi
) : FirebaseRepository {

    override suspend fun getUser(): UserEntity? = firebaseApi.getUser()
        ?.toUserEntity()

    override suspend fun createHistoryDocument(): Task<Void> = firebaseApi.createHistoryDocument()

    override suspend fun addToHistory(
        transactionEntity: TransactionEntity
    ): Task<Void> = firebaseApi.addToHistory(transactionEntity)
}