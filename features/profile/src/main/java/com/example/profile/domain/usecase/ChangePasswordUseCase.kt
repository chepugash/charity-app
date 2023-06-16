package com.example.profile.domain.usecase

import com.example.profile.domain.repository.ProfileRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(
        password: String
    ): Task<Void>? = profileRepository.changePassword(password)
}