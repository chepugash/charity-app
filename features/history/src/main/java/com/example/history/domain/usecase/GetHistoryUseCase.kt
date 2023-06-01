package com.example.history.domain.usecase

import com.example.history.domain.entity.TransactionEntity
import com.example.history.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(): Task<ArrayList<TransactionEntity>> =
        firebaseRepository.getHistory()
}