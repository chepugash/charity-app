package com.example.sign.data

import com.example.sign.data.api.SignApi
import com.example.sign.domain.entity.ApiResult
import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.repository.SignRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(
    private val signApi: SignApi
) : SignRepository {

    override suspend fun signUp(
        signUserEntity: SignUserEntity
    ): Task<AuthResult> {
        if (signUserEntity.password != signUserEntity.repeatPassword) {
            throw Exception("Password mismatch")
        }
        return signApi.signUp(signUserEntity)
    }

    override suspend fun signIn(
        signUserEntity: SignUserEntity
    ): Task<AuthResult> = signApi.signIn(signUserEntity)
}