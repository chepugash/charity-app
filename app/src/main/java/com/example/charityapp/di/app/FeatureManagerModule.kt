package com.example.charityapp.di.app

import com.example.charityapp.di.deps.FeatureHolderManager
import com.example.common.di.FeatureApiHolder
import com.example.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class FeatureManagerModule {

    @ApplicationScope
    @Provides
    fun provideFeatureHolderManager(
        featureApiHolderMap: @JvmSuppressWildcards Map<Class<*>, FeatureApiHolder>
    ): FeatureHolderManager {
        return FeatureHolderManager(featureApiHolderMap)
    }
}