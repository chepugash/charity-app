package com.example.foundation_info.di

import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.FeatureScope
import com.example.foundation_info.data.FoundationRepositoryImpl
import com.example.foundation_info.data.FirebaseRepositoryImpl
import com.example.foundation_info.data.api.firebase.FirebaseApi
import com.example.foundation_info.data.api.firebase.FirebaseApiImpl
import com.example.foundation_info.data.api.foundation.FoundationApi
import com.example.foundation_info.domain.repository.FirebaseRepository
import com.example.foundation_info.domain.repository.FoundationRepository
import com.example.foundation_info.domain.usecase.AddToFavouriteUseCase
import com.example.foundation_info.domain.usecase.GetUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideFirebaseRepository(
        firebaseApi: FirebaseApi
    ): FirebaseRepository = FirebaseRepositoryImpl(firebaseApi)

    @Provides
    @FeatureScope
    fun provideFoundationApi(
        networkApiCreator: NetworkApiCreator,
    ): FoundationApi = networkApiCreator.create(FoundationApi::class.java)

    @Provides
    @FeatureScope
    fun provideFirebaseApi(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
    ): FirebaseApi = FirebaseApiImpl(auth, firestore)
}