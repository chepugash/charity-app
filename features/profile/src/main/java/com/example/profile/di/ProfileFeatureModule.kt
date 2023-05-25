package com.example.profile.di

import com.example.common.di.scope.FeatureScope
import com.example.profile.data.ProfileRepositoryImpl
import com.example.profile.data.api.ProfileApi
import com.example.profile.data.api.ProfileApiImpl
import com.example.profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class ProfileFeatureModule {

    @Provides
    @FeatureScope
    fun provideProfileRepository(
        profileRepository: ProfileRepositoryImpl
    ): ProfileRepository = profileRepository

    @Provides
    @FeatureScope
    fun provideProfileApi(
        apiAuth: FirebaseAuth,
    ): ProfileApi = ProfileApiImpl(apiAuth)
}