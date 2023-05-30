package com.example.foundation_info.domain.repository

import com.example.foundation_info.domain.entity.FoundationEntity

interface FoundationRepository {

    suspend fun getFoundation(query: Long): FoundationEntity
}