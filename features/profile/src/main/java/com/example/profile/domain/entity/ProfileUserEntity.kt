package com.example.profile.domain.entity

data class ProfileUserEntity(
    val id: String,
    var email: String,
    var name: String = "?",
)