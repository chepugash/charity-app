package com.example.profile.domain.usecase

import com.example.profile.domain.repository.ProfileRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class DeleteProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend fun deleteProfile(): Task<Void>? = profileRepository.deleteProfile()
}