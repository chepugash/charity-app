package com.example.history.di

import com.example.common.di.scope.FeatureScope
import com.example.history.data.HistoryRepositoryImpl
import com.example.history.data.firebase.HistoryApi
import com.example.history.data.firebase.HistoryApiImpl
import com.example.history.domain.repository.HistoryRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class HistoryFeatureModule {

    @Provides
    @FeatureScope
    fun provideFirebaseRepository(
        historyApi: HistoryApi
    ): HistoryRepository = HistoryRepositoryImpl(historyApi)

    @Provides
    @FeatureScope
    fun provideFirebaseApi(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
    ): HistoryApi = HistoryApiImpl(auth, firestore)
}