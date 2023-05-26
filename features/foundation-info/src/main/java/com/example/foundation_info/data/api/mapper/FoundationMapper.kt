package com.example.foundation_info.data.api.mapper

import com.example.foundation_info.data.api.response.FoundationResponse
import com.example.foundation_info.domain.entity.FoundationEntity

fun FoundationResponse.toFoundationEntity(): FoundationEntity = FoundationEntity(
    id = id,
    account = account,
    address = address,
    image = image.src.substring(0, image.src.length - 4),
    phone = phone,
    name = name,
    website = website,
    description = description
)