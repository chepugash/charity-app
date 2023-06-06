package com.example.history.data.firebase

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface HistoryApi {

    suspend fun getHistory(): Flow<List<HashMap<String, Any>>>

    suspend fun getUser(): FirebaseUser?
}