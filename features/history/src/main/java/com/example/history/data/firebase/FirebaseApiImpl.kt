package com.example.history.data.firebase

import com.example.history.domain.entity.TransactionEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseApiImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : FirebaseApi {

    override suspend fun getHistory(): Task<ArrayList<FirebaseTransactionEntity>> =
        firestore.collection(COLLECTION)
            .document(auth.currentUser?.uid.toString())
            .get().continueWith { task ->
                val history = ArrayList<FirebaseTransactionEntity>()
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        val result = document.get(FIELD) as? ArrayList<HashMap<String, out Any>>
                        if (result != null) {
                            for (item in result) {
                                history.add(FirebaseTransactionEntity(
                                    date = item["date"] as Timestamp,
                                    foundationId = item["foundationId"] as Long,
                                    foundationName = item["foundationName"].toString(),
                                    sum = item["sum"] as Long
                                ))
                            }
                        }
                    }
                }
                history
            }

    companion object {
        private const val COLLECTION = "users"
        private const val FIELD = "history"
    }
}