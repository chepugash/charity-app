package com.example.foundation_info.data.api.firebase

import com.example.foundation_info.domain.entity.FoundationUserEntity
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toFoundationUserEntity(): FoundationUserEntity = FoundationUserEntity(id = uid)