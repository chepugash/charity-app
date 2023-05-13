package com.example.sign.data

import com.example.sign.data.api.SignApi
import com.example.sign.domain.entity.AuthResult
import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.repository.SignRepository
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(
    private val signApi: SignApi
) : SignRepository {

    override suspend fun signUp(
        signUserEntity: SignUserEntity
    ): AuthResult {
        if (signUserEntity.password != signUserEntity.repeatPassword) {
            throw Exception("Password mismatch")
        }
        return signApi.signUp(signUserEntity)
    }

    override suspend fun signIn(
        signUserEntity: SignUserEntity
    ): AuthResult = signApi.signIn(signUserEntity)
}