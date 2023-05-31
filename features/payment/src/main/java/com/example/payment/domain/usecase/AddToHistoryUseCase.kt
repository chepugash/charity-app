package com.example.payment.domain.usecase

import com.example.payment.domain.entity.TransactionEntity
import com.example.payment.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class AddToHistoryUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(
        transactionEntity: TransactionEntity
    ): Task<Void> = firebaseRepository.addToHistory(transactionEntity)
}