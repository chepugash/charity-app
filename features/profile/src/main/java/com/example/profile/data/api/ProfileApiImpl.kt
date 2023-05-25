package com.example.profile.data.api

import com.example.profile.domain.entity.ApiResult
import com.example.profile.domain.entity.ProfileUserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.Api
import javax.inject.Inject

class ProfileApiImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : ProfileApi {

    override suspend fun getUser(): FirebaseUser? = auth.currentUser

    override suspend fun changeUserName(name: String): Task<Void>? {
        val user = getUser()
        val updates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .build()
        return user?.updateProfile(updates)
    }

    override suspend fun changeUserPassword(
        password: String
    ): Task<Void>? = getUser()?.updatePassword(password)

    override suspend fun deleteProfile(): Task<Void>? = getUser()?.delete()

    override suspend fun signOut() {
        auth.signOut()
    }
}