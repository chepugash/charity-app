package com.example.foundation_info.data.api.firebase.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private fun HashMap<String, out Any>.toFoundationId(): Long {
    return get("id") as Long
}

fun Flow<List<HashMap<String, out Any>>>.toFoundationIdList(): Flow<List<Long>>
        = map { list ->
    list.map { entity -> entity.toFoundationId() }
}