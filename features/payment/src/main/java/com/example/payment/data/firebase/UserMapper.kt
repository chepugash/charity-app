package com.example.payment.data.firebase

import com.example.payment.domain.entity.UserEntity
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUserEntity(): UserEntity = UserEntity(id = uid)