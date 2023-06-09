package com.example.profile.domain.usecase

import com.example.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke() {
        profileRepository.signOut()
    }
}