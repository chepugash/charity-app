package com.example.favourite.domain.usecase

import com.example.favourite.domain.entity.UserEntity
import com.example.favourite.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(): UserEntity? = firebaseRepository.getUser()
}