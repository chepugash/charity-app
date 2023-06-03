package com.example.common.di

import android.content.Context
import com.example.common.core.ResourceManager
import com.example.common.core.config.AppProperties
import com.example.common.data.network.NetworkApiCreator
import com.example.common.data.storage.dao.FoundationDao
import com.example.common.data.storage.dao.UserDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface CommonApi {

    fun context(): Context

    fun provideResourceManager(): ResourceManager

    fun provideNetworkApiCreator(): NetworkApiCreator

    fun provideFirestore(): FirebaseFirestore

    fun provideFirebaseAuth(): FirebaseAuth

    fun provideAppProperties(): AppProperties

    fun provideUserDao(): UserDao

    fun provideFoundationDao(): FoundationDao
}