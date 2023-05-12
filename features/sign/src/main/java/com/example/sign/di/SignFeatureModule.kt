package com.example.sign.di

import android.content.Context
import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.ApplicationScope
import com.example.common.di.scope.FeatureScope
import com.example.sign.data.SignApi
import com.example.sign.data.SignApiImpl
import com.example.sign.domain.repository.SignRepository
import com.example.sign.data.SignRepositoryImpl
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
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
    fun provideSignApi(api: FirebaseFirestore): SignApi {
        return SignApiImpl(api)
    }
}