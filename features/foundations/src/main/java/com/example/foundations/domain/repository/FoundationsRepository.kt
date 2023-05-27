package com.example.foundations.domain.repository

import com.example.foundations.domain.entity.CategoryEntity

interface FoundationsRepository {

    suspend fun getFoundations(id: Int): CategoryEntity
}