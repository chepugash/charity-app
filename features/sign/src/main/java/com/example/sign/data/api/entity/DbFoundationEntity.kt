package com.example.sign.data.api.entity

data class DbFoundationEntity(
    val id: Long,
    val account: String,
    val address: String,
    val description: String,
    val image: String,
    val phone: String,
    val name: String,
    val website: String
)