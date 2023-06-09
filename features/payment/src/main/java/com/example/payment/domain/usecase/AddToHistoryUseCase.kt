package com.example.payment.domain.usecase

import com.example.payment.domain.entity.TransactionEntity
import com.example.payment.domain.repository.PaymentRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class AddToHistoryUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {

    suspend operator fun invoke(
        transactionEntity: TransactionEntity
    ): Task<Void> = paymentRepository.addToHistory(transactionEntity)
}