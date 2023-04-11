package com.example.sign_up.domain.usecase

import com.example.sign_up.domain.entity.SignUpUserEntity
import com.example.sign_up.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {

    suspend fun signUp(signUpUserEntity: SignUpUserEntity) {
        signUpRepository.signUp(signUpUserEntity)
    }
}