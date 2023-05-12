package com.example.common.di

import android.content.Context
import com.example.common.core.ResourceManager
import com.example.common.core.config.AppProperties
import com.example.common.data.network.NetworkApiCreator
import com.google.firebase.firestore.FirebaseFirestore

interface CommonApi {

    fun context(): Context

    fun provideResourceManager(): ResourceManager

    fun provideNetworkApiCreator(): NetworkApiCreator

    fun provideFirestore(): FirebaseFirestore

    fun provideAppProperties(): AppProperties
}