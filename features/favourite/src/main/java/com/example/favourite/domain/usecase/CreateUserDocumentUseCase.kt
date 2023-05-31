package com.example.favourite.domain.usecase

import com.example.favourite.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class CreateUserDocumentUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(): Task<Void> = firebaseRepository.createUserDocument()
}