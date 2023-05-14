package com.example.profile.data.api

import com.example.profile.domain.entity.ApiResult
import com.example.profile.domain.entity.ProfileUserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ProfileApiImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : ProfileApi {

    override suspend fun getUser(): FirebaseUser? {
        return auth.currentUser
    }

    override suspend fun changeUserName(userId: String, name: String): ApiResult {
        return null!!
    }

    override suspend fun changeUserPassword(
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