package com.example.categories.di

import com.example.categories.data.CategoriesRepositoryImpl
import com.example.categories.data.api.CategoriesApi
import com.example.categories.domain.repository.CategoriesRepository
import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class CategoriesFeatureModule {

    @Provides
    @FeatureScope
    fun provideCategoriesRepository(
        categoriesApi: CategoriesApi
    ): CategoriesRepository = CategoriesRepositoryImpl(categoriesApi)

    @Provides
    @FeatureScope
    fun provideCategoriesApi(
        networkApiCreator: NetworkApiCreator,
    ): CategoriesApi = networkApiCreator.create(CategoriesApi::class.java)
}