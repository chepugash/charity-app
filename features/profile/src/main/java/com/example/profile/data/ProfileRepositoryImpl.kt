package com.example.profile.data

import com.example.profile.data.api.ProfileApi
import com.example.profile.data.api.toProfileUserEntity
import com.example.profile.domain.entity.ApiResult
import com.example.profile.domain.entity.ProfileUserEntity
import com.example.profile.domain.repository.ProfileRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileRepository {

    override suspend fun getUser(): ProfileUserEntity? {
        return profileApi.getUser()?.toProfileUserEntity()
    }

    override suspend fun changeName(name: String): Task<Void>? {
        return profileApi.changeUserName(name)
    }

    override suspend fun changePassword(
        password: String
    ): Task<Void>? {
        return profileApi.changeUserPassword(password)
    }

    override suspend fun deleteProfile(userId: String): ApiResult {
        return null!!
    }
}