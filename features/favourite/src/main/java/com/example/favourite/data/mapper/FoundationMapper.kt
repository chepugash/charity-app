package com.example.favourite.data.mapper

import com.example.favourite.domain.entity.FoundationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private fun HashMap<String, out Any>.toFoundationEntity(): FoundationEntity = FoundationEntity(
    id = get("id") as Long,
    name = get("name") as String,
    image = get("image") as String
)

fun Flow<List<HashMap<String, out Any>>>.toFoundationList(): Flow<List<FoundationEntity>>
    = map { list ->
        list.map { entity ->
            entity.toFoundationEntity()
        }
    }
