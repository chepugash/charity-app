package com.example.foundation_info.domain.usecase

import com.example.foundation_info.domain.entity.FoundationUserEntity
import com.example.foundation_info.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(): FoundationUserEntity? = firebaseRepository.getUser()
}