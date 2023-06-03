package com.example.favourite.data.firebase

import com.example.favourite.data.mapper.toFoundationEntityList
import com.example.favourite.domain.entity.FoundationEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseApiImpl  @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : FirebaseApi {

    override suspend fun getUser(): FirebaseUser? = auth.currentUser

    override suspend fun createUserDocument(): Task<Void> = firestore
        .collection(COLLECTION)
        .document(auth.currentUser?.uid.toString())
        .set(hashMapOf(FIELD to arrayListOf<FoundationEntity>()))

//    override suspend fun getFavourite(): ArrayList<HashMap<String, out Any>>? {
//        return firestore.collection(COLLECTION)
//            .document(getUser()?.uid.toString()).get().result
//            .get(FIELD) as? ArrayList<HashMap<String, out Any>>
//    }

    override suspend fun getFavourite(): Task<ArrayList<FoundationEntity>> = firestore.collection(COLLECTION)
        .document(getUser()?.uid.toString())
        .get().continueWith { task ->
            val favourite = ArrayList<FoundationEntity>()
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    val result = document.get(FIELD) as? ArrayList<HashMap<String, out Any>>
                    if (result != null) {
                        for (item in result) {
                            favourite.add(FoundationEntity(
                                id = item["id"] as Long,
                                image = item["image"].toString(),
                                name = item["name"].toString()
                            ))
                        }
                    }
                }
            }
            favourite
        }

    companion object {
        private const val COLLECTION = "users"
        private const val FIELD = "favorite"
    }
}