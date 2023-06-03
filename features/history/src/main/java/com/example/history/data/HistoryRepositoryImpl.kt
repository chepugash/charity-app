package com.example.history.data

import com.example.history.data.firebase.HistoryApi
import com.example.history.domain.entity.TransactionEntity
import com.example.history.domain.repository.HistoryRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyApi: HistoryApi
) : HistoryRepository {

    override suspend fun getHistory(): Task<ArrayList<TransactionEntity>> =
        historyApi.getHistory()
}