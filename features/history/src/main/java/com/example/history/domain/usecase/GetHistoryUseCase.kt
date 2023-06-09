package com.example.history.domain.usecase

import com.example.history.domain.entity.TransactionEntity
import com.example.history.domain.repository.HistoryRepository
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {

    suspend operator fun invoke(): Flow<List<TransactionEntity>> =
        historyRepository.getHistory()
}