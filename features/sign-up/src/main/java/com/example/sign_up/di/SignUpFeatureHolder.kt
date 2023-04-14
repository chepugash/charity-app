package com.example.sign_up.di

import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.sign_up.SignUpRouter
import javax.inject.Inject

class SignUpFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val signUpRouter: SignUpRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val signUpFeatureDependencies =
            DaggerSignUpFeatureComponent_SignUpFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerSignUpFeatureComponent.builder()
            .withDependencies(signUpFeatureDependencies)
            .router(signUpRouter)
            .build()
    }
}