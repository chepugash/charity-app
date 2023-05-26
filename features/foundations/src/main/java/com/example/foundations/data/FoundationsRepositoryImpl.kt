package com.example.foundations.data

import com.example.foundations.data.api.FoundationsApi
import com.example.foundations.data.api.mapper.toCategoryEntity
import com.example.foundations.data.api.mapper.toFoundationEntityList
import com.example.foundations.domain.entity.CategoryEntity
import com.example.foundations.domain.entity.FoundationEntity
import com.example.foundations.domain.repository.FoundationsRepository
import javax.inject.Inject

class FoundationsRepositoryImpl @Inject constructor(
    private val api: FoundationsApi
) : FoundationsRepository {

    override suspend fun getFoundations(
        query: Int
    ): CategoryEntity = api.getCategory(query).toCategoryEntity()
}