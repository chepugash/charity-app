package com.example.sign.domain.repository

import com.example.sign.domain.entity.ApiResult
import com.example.sign.domain.entity.SignUserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface SignRepository {

    suspend fun signUp(signUserEntity: SignUserEntity): Task<AuthResult>

    suspend fun signIn(signUserEntity: SignUserEntity): Task<AuthResult>
}