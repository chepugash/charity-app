package com.example.history.domain.repository

import com.example.history.domain.entity.TransactionEntity
import com.google.android.gms.tasks.Task

interface FirebaseRepository {

    suspend fun getHistory(): Task<ArrayList<TransactionEntity>>
}