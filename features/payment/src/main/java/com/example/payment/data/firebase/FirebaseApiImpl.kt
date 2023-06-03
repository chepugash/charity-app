package com.example.payment.data.firebase

import com.example.payment.domain.entity.TransactionEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseApiImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : FirebaseApi {

    override suspend fun getUser(): FirebaseUser? = auth.currentUser

    override suspend fun createHistoryDocument(): Task<Void> = firestore
        .collection(COLLECTION)
        .document(auth.currentUser?.uid.toString())
        .set(hashMapOf(FIELD to arrayListOf<FirebaseTransactionEntity>()))

    override suspend fun addToHistory(
        firebaseTransactionEntity: FirebaseTransactionEntity
    ): Task<Void> = firestore.collection(COLLECTION)
        .document(getUser()?.uid.toString())
        .update(FIELD, FieldValue.arrayUnion(firebaseTransactionEntity))

    companion object {
        private const val COLLECTION = "users"
        private const val FIELD = "history"
    }
}