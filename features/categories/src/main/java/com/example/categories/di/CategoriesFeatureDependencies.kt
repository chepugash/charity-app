package com.example.categories.di

import com.example.common.data.network.NetworkApiCreator
import com.google.firebase.auth.FirebaseAuth

interface CategoriesFeatureDependencies {

    fun networkApiCreator(): NetworkApiCreator

    fun firebaseAuth(): FirebaseAuth
}