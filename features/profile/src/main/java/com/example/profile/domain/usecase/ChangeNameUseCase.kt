package com.example.profile.domain.usecase

import com.example.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ChangeNameUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
}