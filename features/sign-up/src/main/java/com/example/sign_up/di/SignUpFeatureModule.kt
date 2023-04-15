package com.example.sign_up.di

import com.example.common.di.scope.FeatureScope
import com.example.sign_up.domain.repository.SignUpRepository
import com.example.sign_up.domain.repository.SignUpRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class SignUpFeatureModule {

    @Provides
    @FeatureScope
    fun provideSignUpRepository(
        signUpRepository: SignUpRepositoryImpl
    ): SignUpRepository = signUpRepository
}