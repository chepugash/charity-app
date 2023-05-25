package com.example.foundations.di

import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.foundations.FoundationsRouter
import javax.inject.Inject

class FoundationsFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val foundationsRouter: FoundationsRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val categoriesFeatureDependencies =
            DaggerFoundationsFeatureComponent_FoundationsFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerFoundationsFeatureComponent.builder()
            .withDependencies(categoriesFeatureDependencies)
            .router(foundationsRouter)
            .build()
    }
}