package com.example.foundation_info.data.api.firebase

import com.example.foundation_info.domain.entity.FoundationEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseApiImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : FirebaseApi {

    override suspend fun getUser(): FirebaseUser? = auth.currentUser

    override suspend fun addToFavourite(
        foundationEntity: FoundationEntity
    ): Task<Void> = firestore.collection(COLLECTION)
        .document(getUser()?.uid.toString())
        .update(FIELD, FieldValue.arrayUnion(foundationEntity.id))


    override suspend fun removeFromFavourite(
        foundationEntity: FoundationEntity
    ): Task<Void> = firestore.collection(COLLECTION)
        .document(getUser()?.uid.toString())
        .update(FIELD, FieldValue.arrayRemove(foundationEntity.id))

    override suspend fun getFavourite(): Task<ArrayList<Long>> = firestore.collection(COLLECTION)
            .document(getUser()?.uid.toString())
            .get().continueWith { task ->
            val favourite = ArrayList<Long>()
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        val result = document.get(FIELD) as? ArrayList<Long>
                        result?.let {
                            favourite.addAll(it)
                        }
                    }
                }
                favourite
            }

    companion object {
        const val COLLECTION = "users"
        const val FIELD = "favorite"
    }
}