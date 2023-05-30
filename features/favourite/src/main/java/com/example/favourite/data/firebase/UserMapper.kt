package com.example.favourite.data.firebase

import com.example.favourite.domain.entity.UserEntity
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUserEntity(): UserEntity = UserEntity(id = uid)