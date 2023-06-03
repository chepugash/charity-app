package com.example.history.data.firebase

import com.example.history.domain.entity.TransactionEntity
import com.google.android.gms.tasks.Task

interface HistoryApi {

    suspend fun getHistory(): Task<ArrayList<TransactionEntity>>
}