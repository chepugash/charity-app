package com.example.profile.data

import com.example.profile.data.api.ProfileApi
import com.example.profile.data.api.toProfileUserEntity
import com.example.profile.domain.entity.ApiResult
import com.example.profile.domain.entity.ProfileUserEntity
import com.example.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileRepository {

    override suspend fun getUser(): ProfileUserEntity? {
        return profileApi.getUser()?.toProfileUserEntity()
    }

    override suspend fun changeName(userId: String, name: String): ApiResult {
        return null!!
    }

    override suspend fun changePassword(
        userId: String,
        password: String,
        repeatPassword: String
    ): ApiResult {
        return null!!
    }

    override suspend fun deleteProfile(userId: String): ApiResult {
        return null!!
    }
}