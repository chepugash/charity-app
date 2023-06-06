package com.example.charityapp

import com.example.categories.presentation.CategoriesViewModel
import com.example.categories.presentation.adapter.CategoryItemViewHolder
import com.example.charityapp.di.app.AppComponent
import com.example.charityapp.navigation.Navigator
import com.example.common.data.network.NetworkApiCreator
import com.example.common.data.storage.AppDatabase
import com.example.common.di.modules.NetworkModule
import com.example.favourite.data.firebase.FirebaseApiImpl

// Все технологии, используемые в проекте

class AgonaReview {

    // Multi-modules, Clean, MVVM

    //  Retrofit
    val retrofit = NetworkApiCreator::class

    //  OkHttp
    val okHttp = NetworkApiCreator::class

    //  GsonConverter
    val gsonConverter = NetworkApiCreator::class

    //  Room
    val room = AppDatabase::class

    //  Dagger 2
    val dagger2 = AppComponent::class

    //  Coroutines, Flow
    val coroutinesFlow = FirebaseApiImpl::class

    //  LiveData
    val liveData = CategoriesViewModel::class

    //  Firebase Authentication
    val firebaseAuth = NetworkModule::class

    //  Firebase Firestore
    val firebaseFirestore = NetworkModule::class

    //  Jetpack NavigationComponent
    val jetpackNavComponent = Navigator::class

    //  Coil
    val coil = CategoryItemViewHolder::class

    //  Timber
    val timber = App::class

}