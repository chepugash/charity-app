package com.example.sign.data.api

import com.example.sign.domain.entity.ApiResult
import com.example.sign.domain.entity.FoundationEntity
import com.example.sign.domain.entity.SignUserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
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

    override suspend fun createUserDocument(): Task<Void> = firestore
        .collection("users")
        .document(auth.currentUser?.uid.toString())
        .set(hashMapOf("favorite" to arrayListOf<FoundationEntity>()))
}