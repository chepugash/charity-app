package com.example.history.domain.repository

import com.example.history.domain.entity.TransactionEntity
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    suspend fun getHistory(): Flow<List<TransactionEntity>>
}