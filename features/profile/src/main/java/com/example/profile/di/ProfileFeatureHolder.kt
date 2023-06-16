package com.example.profile.di

import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.profile.ProfileRouter
import javax.inject.Inject

class ProfileFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val profileRouter: ProfileRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val profileFeatureDependencies =
            DaggerProfileFeatureComponent_ProfileFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerProfileFeatureComponent.builder()
            .withDependencies(profileFeatureDependencies)
            .router(profileRouter)
            .build()
    }
}