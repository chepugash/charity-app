package com.example.foundation_info.data

import com.example.foundation_info.data.api.FoundationApi
import com.example.foundation_info.data.api.mapper.toFoundationEntity
import com.example.foundation_info.domain.entity.FoundationEntity
import com.example.foundation_info.domain.repository.FoundationRepository
import javax.inject.Inject

class FoundationRepositoryImpl @Inject constructor(
    private val api: FoundationApi
) : FoundationRepository {

    override suspend fun getFoundation(
        query: Int
    ): FoundationEntity = api.getFoundation(query).toFoundationEntity()
}