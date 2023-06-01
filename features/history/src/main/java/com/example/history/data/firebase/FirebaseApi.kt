package com.example.history.data.firebase

import com.google.android.gms.tasks.Task

interface FirebaseApi {

    suspend fun getHistory(): Task<ArrayList<FirebaseTransactionEntity>>
}