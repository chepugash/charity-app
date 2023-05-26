package com.example.foundations.di

import com.example.common.data.network.NetworkApiCreator
import com.google.firebase.auth.FirebaseAuth

interface FoundationsFeatureDependencies {

    fun networkApiCreator(): NetworkApiCreator

    fun firebaseAuth(): FirebaseAuth
}