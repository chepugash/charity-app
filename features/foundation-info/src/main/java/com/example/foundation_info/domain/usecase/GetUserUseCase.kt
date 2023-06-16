package com.example.foundation_info.domain.usecase

import com.example.foundation_info.domain.entity.UserEntity
import com.example.foundation_info.domain.repository.FoundationRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val foundationRepository: FoundationRepository
) {

    suspend operator fun invoke(): UserEntity? = foundationRepository.getUser()
}