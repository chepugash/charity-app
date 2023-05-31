package com.example.foundation_info.data.api.firebase

import com.example.foundation_info.domain.entity.UserEntity
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toFoundationUserEntity(): UserEntity = UserEntity(id = uid)