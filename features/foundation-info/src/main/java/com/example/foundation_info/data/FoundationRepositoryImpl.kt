package com.example.foundation_info.data

import com.example.foundation_info.data.api.FoundationApi
import com.example.foundation_info.domain.repository.FoundationRepository
import javax.inject.Inject

class FoundationRepositoryImpl @Inject constructor(
    private val api: FoundationApi
) : FoundationRepository {
}