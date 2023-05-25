package com.example.foundations.data.api.mapper

import com.example.foundations.data.api.response.Foundation
import com.example.foundations.domain.entity.FoundationEntity

private fun Foundation.toFoundationEntity(): FoundationEntity = FoundationEntity(
    id = id,
    name = name,
    image = image.src.substring(0, image.src.length - 4)
)

fun List<Foundation>.toFoundationEntityList(): List<FoundationEntity> = map {
    it.toFoundationEntity()
}