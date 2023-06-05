package com.example.favourite.data.firebase

import com.example.favourite.domain.entity.FoundationEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseApi {

    suspend fun getUser(): FirebaseUser?

    suspend fun getFavourite(): Flow<List<HashMap<String, Any>>>
}