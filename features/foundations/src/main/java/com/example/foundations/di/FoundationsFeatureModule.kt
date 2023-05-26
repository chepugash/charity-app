package com.example.foundations.di

import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.FeatureScope
import com.example.foundations.data.FoundationsRepositoryImpl
import com.example.foundations.data.api.FoundationsApi
import com.example.foundations.domain.repository.FoundationsRepository
import dagger.Module
import dagger.Provides

@Module
class FoundationsFeatureModule {

    @Provides
    @FeatureScope
    fun provideFoundationsRepository(
        foundationsApi: FoundationsApi
    ): FoundationsRepository = FoundationsRepositoryImpl(foundationsApi)

    @Provides
    @FeatureScope
    fun provideFoundationsApi(
        networkApiCreator: NetworkApiCreator,
    ): FoundationsApi = networkApiCreator.create(FoundationsApi::class.java)
}