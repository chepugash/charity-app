package com.example.history.data.firebase

import com.example.history.domain.entity.TransactionEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface HistoryApi {

    suspend fun getHistory(): Flow<List<HashMap<String, Any>>>
    suspend fun getUser(): FirebaseUser?
}