package com.example.profile.data.api

import com.example.profile.domain.entity.ApiResult
import com.example.profile.domain.entity.ProfileUserEntity
import com.google.firebase.auth.FirebaseUser

interface ProfileApi {

    suspend fun getUser(): FirebaseUser?

    suspend fun changeUserName(userId: String, name: String): ApiResult

    suspend fun changeUserPassword(
        userId: String,
        password: String,
        repeatPassword: String
    ): ApiResult

    suspend fun deleteProfile(userId: String): ApiResult
}