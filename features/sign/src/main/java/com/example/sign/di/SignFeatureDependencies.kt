package com.example.sign.di

import com.example.common.data.network.NetworkApiCreator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface SignFeatureDependencies {

    fun networkApiCreator(): NetworkApiCreator

    fun firebaseAuth(): FirebaseAuth
}