package com.example.foundation_info.data.api.foundation.mapper

import com.example.foundation_info.data.api.foundation.response.FoundationResponse
import com.example.foundation_info.domain.entity.FoundationEntity

fun FoundationResponse.toFoundationEntity(): FoundationEntity = FoundationEntity(
    id = id,
    account = account,
    address = address,
    image = image.src.substringBefore("."),
    phone = phone,
    name = name,
    website = website,
    description = description
)