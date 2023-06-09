package com.example.profile.domain.usecase

import com.example.profile.domain.repository.ProfileRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class ChangeNameUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(name: String): Task<Void>? = profileRepository.changeName(name)
}