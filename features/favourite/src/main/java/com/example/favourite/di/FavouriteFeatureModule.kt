package com.example.favourite.di

import com.example.common.data.network.NetworkApiCreator
import com.example.common.data.storage.dao.FoundationDao
import com.example.common.di.scope.FeatureScope
import com.example.favourite.data.FavouriteRepositoryImpl
import com.example.favourite.data.favourite.FavouriteApi
import com.example.favourite.data.firebase.FirebaseApi
import com.example.favourite.data.firebase.FirebaseApiImpl
import com.example.favourite.domain.repository.FavouriteRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class FavouriteFeatureModule {

    @Provides
    @FeatureScope
    fun provideFavouriteRepository(
        favouriteApi: FavouriteApi,
        firebaseApi: FirebaseApi
    ): FavouriteRepository = FavouriteRepositoryImpl(
        favouriteApi,
        firebaseApi,
    )

    @Provides
    @FeatureScope
    fun provideFavouriteApi(
        networkApiCreator: NetworkApiCreator,
    ): FavouriteApi = networkApiCreator.create(FavouriteApi::class.java)

    @Provides
    @FeatureScope
    fun provideFirebaseApi(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
    ): FirebaseApi = FirebaseApiImpl(auth, firestore)
}