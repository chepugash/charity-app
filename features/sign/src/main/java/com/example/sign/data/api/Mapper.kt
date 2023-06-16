package com.example.sign.data.api

import com.example.sign.domain.entity.SessionUserEntity
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toSessionUserEntity(): SessionUserEntity = SessionUserEntity(
    id = uid
)