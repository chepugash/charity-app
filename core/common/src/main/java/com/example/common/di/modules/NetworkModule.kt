package com.example.common.di.modules

import com.example.common.BuildConfig
import com.example.common.core.config.AppProperties
import com.example.common.core.config.NetworkProperties
import com.example.common.data.network.NetworkApiCreator
import com.example.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

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
}