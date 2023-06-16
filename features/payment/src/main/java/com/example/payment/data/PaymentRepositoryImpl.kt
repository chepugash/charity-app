package com.example.payment.data

import com.example.payment.data.firebase.FirebaseApi
import com.example.payment.data.firebase.mapper.toFirebaseTransactionEntity
import com.example.payment.data.firebase.mapper.toUserEntity
import com.example.payment.data.payment.PaymentApi
import com.example.payment.domain.entity.TransactionEntity
import com.example.payment.domain.entity.UserEntity
import com.example.payment.domain.repository.PaymentRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentApi: PaymentApi,
    private val firebaseApi: FirebaseApi
) : PaymentRepository {

    override suspend fun getUser(): UserEntity? = firebaseApi.getUser()
        ?.toUserEntity()

    override suspend fun addToHistory(
        transactionEntity: TransactionEntity
    ): Task<Void> = firebaseApi.addToHistory(transactionEntity.toFirebaseTransactionEntity())
}