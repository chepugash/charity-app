package com.example.sign.domain.entity

data class SignUserEntity(
    val email: String,
    val password: String,
    val repeatPassword: String = ""
)