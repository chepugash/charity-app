package com.example.sign.di

import com.example.common.di.scope.FeatureScope
import com.example.sign.domain.repository.SignRepository
import com.example.sign.domain.repository.SignRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class SignFeatureModule {

    @Provides
    @FeatureScope
    fun provideSignRepository(
        signRepository: SignRepositoryImpl
    ): SignRepository = signRepository
}