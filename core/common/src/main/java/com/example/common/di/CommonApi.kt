package com.example.common.di

import android.content.Context
import com.example.common.core.ResourceManager
import com.example.common.core.config.AppProperties
import com.example.common.data.network.NetworkApiCreator

interface CommonApi {

    fun context(): Context

    fun provideResourceManager(): ResourceManager

    fun provideNetworkApiCreator(): NetworkApiCreator

    fun provideAppProperties(): AppProperties
}