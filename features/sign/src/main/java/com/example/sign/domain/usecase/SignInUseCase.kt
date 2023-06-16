package com.example.sign.domain.usecase

import com.example.sign.domain.entity.SignUserEntity
import com.example.sign.domain.repository.SignRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signRepository: SignRepository
) {

    suspend operator fun invoke(
        signUserEntity: SignUserEntity
    ): Task<AuthResult> = signRepository.signIn(signUserEntity)
}