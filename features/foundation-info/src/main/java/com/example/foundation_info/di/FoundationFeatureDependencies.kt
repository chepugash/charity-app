package com.example.foundation_info.di

import com.example.common.data.network.NetworkApiCreator
import com.example.common.data.storage.dao.FoundationDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface FoundationFeatureDependencies {

    fun networkApiCreator(): NetworkApiCreator

    fun firebaseAuth(): FirebaseAuth

    fun firebaseFirestore(): FirebaseFirestore
}