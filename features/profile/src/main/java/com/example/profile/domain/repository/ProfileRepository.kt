package com.example.profile.domain.repository

import com.example.profile.domain.entity.ApiResult
import com.example.profile.domain.entity.ProfileUserEntity

interface ProfileRepository {

    suspend fun getUser(): ProfileUserEntity?

    suspend fun changeName(userId: String, name: String): ApiResult

    suspend fun changePassword(userId: String,
        password: String,
        repeatPassword: String
    ): ApiResult

    suspend fun deleteProfile(userId: String): ApiResult
}