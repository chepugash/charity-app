package com.example.history.data

import com.example.history.data.firebase.FirebaseApi
import com.example.history.domain.entity.TransactionEntity
import com.example.history.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseApi: FirebaseApi
) : FirebaseRepository {

    override suspend fun getHistory(): Task<ArrayList<TransactionEntity>> =
        firebaseApi.getHistory()
}