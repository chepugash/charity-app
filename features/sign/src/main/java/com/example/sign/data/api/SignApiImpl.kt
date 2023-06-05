package com.example.sign.data.api

import com.example.sign.data.api.entity.DbFoundationEntity
import com.example.sign.data.api.entity.DbTransactionEntity
import com.example.sign.domain.entity.SignUserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignApiImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : SignApi {

    override suspend fun signUp(
        userEntity: SignUserEntity
    ): Task<AuthResult> = auth.createUserWithEmailAndPassword(
            userEntity.email,
            userEntity.password
        )

    override suspend fun signIn(
        userEntity: SignUserEntity
    ): Task<AuthResult> = auth.signInWithEmailAndPassword(
        userEntity.email,
        userEntity.password
    )

    override suspend fun getUser(): FirebaseUser? = auth.currentUser

    override suspend fun createUserDocument(): Task<Void> = firestore
        .collection(COLLECTION)
        .document(auth.currentUser?.uid.toString())
        .set(hashMapOf(FIELD_FAVOURITE to arrayListOf<DbFoundationEntity>(),
            FIELD_HISTORY to arrayListOf<DbTransactionEntity>()))

    companion object {
        private const val COLLECTION = "users"
        private const val FIELD_FAVOURITE = "favorite"
        private const val FIELD_HISTORY = "history"
    }
}