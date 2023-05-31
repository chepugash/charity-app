package com.example.favourite.di

import com.example.common.di.scope.FeatureScope
import com.example.favourite.data.FirebaseRepositoryImpl
import com.example.favourite.data.firebase.FirebaseApi
import com.example.favourite.data.firebase.FirebaseApiImpl
import com.example.favourite.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class FavouriteFeatureModule {

    @Provides
    @FeatureScope
    fun provideFirebaseRepository(
        firebaseApi: FirebaseApi
    ): FirebaseRepository = FirebaseRepositoryImpl(firebaseApi)

    @Provides
    @FeatureScope
    fun provideFirebaseApi(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
    ): FirebaseApi = FirebaseApiImpl(auth, firestore)
}