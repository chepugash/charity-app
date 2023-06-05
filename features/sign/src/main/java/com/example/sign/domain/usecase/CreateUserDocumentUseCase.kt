package com.example.sign.domain.usecase

import com.example.sign.domain.repository.SignRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class CreateUserDocumentUseCase @Inject constructor(
    private val signRepository: SignRepository
) {

    suspend operator fun invoke(): Task<Void> = signRepository.createUserDocument()
}