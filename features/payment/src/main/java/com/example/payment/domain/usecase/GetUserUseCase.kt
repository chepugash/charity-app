package com.example.payment.domain.usecase

import com.example.payment.domain.entity.UserEntity
import com.example.payment.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(): UserEntity? = firebaseRepository.getUser()
}