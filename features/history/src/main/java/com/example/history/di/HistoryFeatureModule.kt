package com.example.history.di

import com.example.common.di.scope.FeatureScope
import com.example.history.data.FirebaseRepositoryImpl
import com.example.history.data.firebase.FirebaseApi
import com.example.history.data.firebase.FirebaseApiImpl
import com.example.history.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class HistoryFeatureModule {

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