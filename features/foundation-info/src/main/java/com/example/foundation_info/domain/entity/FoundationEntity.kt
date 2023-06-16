package com.example.foundation_info.domain.entity

data class FoundationEntity(
    val id: Long,
    val account: String,
    val address: String,
    val description: String,
    val image: String,
    val phone: String,
    val name: String,
    val website: String
)
