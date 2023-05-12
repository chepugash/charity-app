package com.example.common.di.modules

import android.content.Context
import com.example.common.BuildConfig
import com.example.common.core.config.AppProperties
import com.example.common.core.config.NetworkProperties
import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.ApplicationScope
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.ktx.initialize
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideNetworkProperties(appProperties: AppProperties): NetworkProperties {
        return appProperties.networkProperties()
    }

    @Provides
    @ApplicationScope
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(
        networkProperties: NetworkProperties,
        loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(networkProperties.connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(networkProperties.writeTimeout, TimeUnit.SECONDS)
            .readTimeout(networkProperties.readTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @ApplicationScope
    fun provideApiCreator(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        appProperties: AppProperties
    ): NetworkApiCreator {
        return NetworkApiCreator(okHttpClient, gsonConverterFactory, appProperties.getBaseUrl())
    }

    @Provides
    @ApplicationScope
    fun provideFirestore(
        context: Context
    ): FirebaseFirestore {
        val options = FirebaseOptions.Builder()
            .setProjectId("charity-a31da")
            .setApplicationId("1:641315015950:android:ad56eef2d76bcd8b3c5b5f")
            .setApiKey("AIzaSyAD3GD0G5PNbOioXtjdbfvrqiYT2394C7s")
            .build()
        Firebase.initialize(context, options)
        return FirebaseFirestore.getInstance()
    }

}