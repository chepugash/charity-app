package com.example.payment.domain.usecase

import com.example.payment.domain.repository.PaymentRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class CreateHistoryDocumentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {

    suspend operator fun invoke(): Task<Void> = paymentRepository.createHistoryDocument()
}