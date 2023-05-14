package com.example.sign.di

import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.sign.SignRouter
import javax.inject.Inject

class SignFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val signRouter: SignRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val signFeatureDependencies =
            DaggerSignFeatureComponent_SignFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerSignFeatureComponent.builder()
            .withDependencies(signFeatureDependencies)
            .router(signRouter)
            .build()
    }
}