package com.example.profile.data.api

import com.example.profile.domain.entity.ApiResult
import com.example.profile.domain.entity.ProfileUserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface ProfileApi {

    suspend fun getUser(): FirebaseUser?

    suspend fun changeUserName(name: String): Task<Void>?

    suspend fun changeUserPassword(password: String): Task<Void>?

    suspend fun deleteProfile(): Task<Void>?

    suspend fun signOut()
}