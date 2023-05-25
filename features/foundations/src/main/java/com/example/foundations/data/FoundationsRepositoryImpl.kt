package com.example.foundations.data

import com.example.foundations.data.api.FoundationsApi
import com.example.foundations.domain.repository.FoundationsRepository
import javax.inject.Inject

class FoundationsRepositoryImpl @Inject constructor(
    private val api: FoundationsApi
) : FoundationsRepository {
}