package com.example.foundations.domain.entity

data class CategoryEntity(
    val id: Int,
    val name: String,
    val foundations: List<FoundationEntity>
)