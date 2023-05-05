package com.example.sign.domain.usecase

import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.repository.SignRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignRepository
) {

    suspend fun signUp(signUpUserEntity: SignUserEntity) {
        signUpRepository.signUp(signUpUserEntity)
    }
}