package com.example.sign_up.domain.repository

import com.example.sign_up.di.SignUpFeatureApi
import com.example.sign_up.domain.entity.SignUpUserEntity
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
) : SignUpRepository {

    override suspend fun signUp(signUpUserEntity: SignUpUserEntity) {
        return null!!
    }
}