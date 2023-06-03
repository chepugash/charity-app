package com.example.favourite.data

import com.example.common.data.storage.dao.FoundationDao
import com.example.common.data.storage.entity.DbFoundationEntity
import com.example.favourite.data.favourite.FavouriteApi
import com.example.favourite.data.firebase.FirebaseApi
import com.example.favourite.data.mapper.toFoundationEntityList
import com.example.favourite.data.mapper.toUserEntity
import com.example.favourite.domain.entity.FoundationEntity
import com.example.favourite.domain.entity.UserEntity
import com.example.favourite.domain.repository.FavouriteRepository
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteApi: FavouriteApi,
    private val firebaseApi: FirebaseApi,
    private val foundationDao: FoundationDao
) : FavouriteRepository {

    override suspend fun getUser(): UserEntity? = firebaseApi.getUser()?.toUserEntity()

    override suspend fun createUserDocument(): Task<Void> = firebaseApi.createUserDocument()

    override suspend fun getFavourite(): Task<ArrayList<FoundationEntity>> = firebaseApi.getFavourite()

    override fun getFavouriteFromDb(): Flow<List<FoundationEntity>> {
        return foundationDao.getAll().toFoundationEntityList()
    }
}