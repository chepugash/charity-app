package com.example.sign.data.api

import com.example.sign.domain.entity.SignUserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface SignApi {

    suspend fun signUp(userEntity: SignUserEntity): Task<AuthResult>

    suspend fun signIn(userEntity: SignUserEntity): Task<AuthResult>

    suspend fun getUser(): FirebaseUser?

    suspend fun createUserDocument(): Task<Void>
}