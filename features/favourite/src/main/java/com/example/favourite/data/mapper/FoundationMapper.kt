package com.example.favourite.data.mapper

import com.example.common.data.storage.entity.DbFoundationEntity
import com.example.favourite.domain.entity.FoundationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private fun DbFoundationEntity.toFoundationEntity(): FoundationEntity = FoundationEntity(
    id = id,
    name = name,
    image = image
)

fun Flow<List<DbFoundationEntity>>.toFoundationEntityList(): Flow<List<FoundationEntity>>
    = map { dbList ->
        dbList.map { dbEntity ->
            dbEntity.toFoundationEntity()
        }
    }

private fun HashMap<String, out Any>.toFoundationEntity(): FoundationEntity = FoundationEntity(
    id = get("id") as Long,
    name = get("name") as String,
    image = get("image") as String
)

fun ArrayList<HashMap<String, out Any>>.toFoundationEntityList(): ArrayList<FoundationEntity> = map {
    it.toFoundationEntity()
} as ArrayList<FoundationEntity>
