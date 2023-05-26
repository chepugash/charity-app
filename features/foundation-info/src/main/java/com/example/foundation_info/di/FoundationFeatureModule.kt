package com.example.foundation_info.di

import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.FeatureScope
import com.example.foundation_info.data.FoundationRepositoryImpl
import com.example.foundation_info.data.api.FoundationApi
import com.example.foundation_info.domain.repository.FoundationRepository
import dagger.Module
import dagger.Provides

@Module
class FoundationFeatureModule {

    @Provides
    @FeatureScope
    fun provideFoundationRepository(
        foundationApi: FoundationApi
    ): FoundationRepository = FoundationRepositoryImpl(foundationApi)

    @Provides
    @FeatureScope
    fun provideFoundationApi(
        networkApiCreator: NetworkApiCreator,
    ): FoundationApi = networkApiCreator.create(FoundationApi::class.java)
}