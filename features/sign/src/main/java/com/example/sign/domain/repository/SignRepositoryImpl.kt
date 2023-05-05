package com.example.sign.domain.repository

import com.example.sign.domain.entity.SignUserEntity
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(
) : SignRepository {

    override suspend fun signUp(signUpUserEntity: SignUserEntity) {
        return null!!
    }
}