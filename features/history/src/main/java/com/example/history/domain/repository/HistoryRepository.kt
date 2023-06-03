package com.example.history.domain.repository

import com.example.history.domain.entity.TransactionEntity
import com.google.android.gms.tasks.Task

interface HistoryRepository {

    suspend fun getHistory(): Task<ArrayList<TransactionEntity>>
}