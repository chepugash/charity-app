package com.example.foundation_info.di

import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.foundation_info.FoundationRouter
import javax.inject.Inject

class FoundationFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val foundationRouter: FoundationRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val foundationFeatureDependencies =
            DaggerFoundationFeatureComponent_FoundationFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerFoundationFeatureComponent.builder()
            .withDependencies(foundationFeatureDependencies)
            .router(foundationRouter)
            .build()
    }
}