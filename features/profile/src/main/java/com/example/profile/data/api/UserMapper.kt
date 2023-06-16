package com.example.profile.data.api

import com.example.profile.domain.entity.ProfileUserEntity
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toProfileUserEntity(): ProfileUserEntity = ProfileUserEntity(
    id = uid,
    email = email ?: "?",
    name = displayName ?: "?"
)