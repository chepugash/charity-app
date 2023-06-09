package com.example.sign.data

import com.example.sign.data.api.SignApi
import com.example.sign.data.api.toSessionUserEntity
import com.example.sign.domain.entity.SessionUserEntity
import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.repository.SignRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(
    private val signApi: SignApi,
) : SignRepository {

    override suspend fun signUp(
        signUserEntity: SignUserEntity
    ): Task<AuthResult> {
        return if (signUserEntity.password == signUserEntity.repeatPassword) {
            signApi.signUp(signUserEntity)
        } else {
            error("Password mismatch")
        }
    }

    override suspend fun signIn(
        signUserEntity: SignUserEntity
    ): Task<AuthResult> = signApi.signIn(signUserEntity)

    override suspend fun getUser(): SessionUserEntity? {
        return signApi.getUser()?.toSessionUserEntity()
    }

    override suspend fun createUserDocument(): Task<Void> = signApi.createUserDocument()
}