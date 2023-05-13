package com.example.sign.domain.usecase

import com.example.sign.domain.entity.AuthResult
import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.repository.SignRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signRepository: SignRepository
) {

    suspend fun signIn(
        signUserEntity: SignUserEntity
    ): AuthResult = signRepository.signIn(signUserEntity)
}