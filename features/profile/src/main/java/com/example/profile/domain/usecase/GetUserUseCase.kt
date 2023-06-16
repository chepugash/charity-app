package com.example.profile.domain.usecase

import com.example.profile.domain.entity.ProfileUserEntity
import com.example.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(): ProfileUserEntity? = profileRepository.getUser()
}