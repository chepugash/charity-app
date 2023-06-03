package com.example.sign.di

import com.example.common.data.network.NetworkApiCreator
import com.example.common.data.storage.dao.UserDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface SignFeatureDependencies {

    fun networkApiCreator(): NetworkApiCreator

    fun firebaseAuth(): FirebaseAuth

    fun firestore(): FirebaseFirestore

    fun userDao(): UserDao
}