package com.example.sign.di

import com.example.common.di.scope.FeatureScope
import com.example.sign.data.api.SignApi
import com.example.sign.data.api.SignApiImpl
import com.example.sign.domain.repository.SignRepository
import com.example.sign.data.SignRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class SignFeatureModule {

    @Provides
    @FeatureScope
    fun provideSignRepository(
        signRepository: SignRepositoryImpl
    ): SignRepository = signRepository

    @Provides
    @FeatureScope
    fun provideSignApi(
        auth: FirebaseAuth
    ): SignApi = SignApiImpl(auth)
}