package com.example.sign_up.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.sign_up.SignUpRouter
import com.example.sign_up.presentation.di.SignUpComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        SignUpFeatureDependencies::class,
    ],
    modules = [
        SignUpFeatureModule::class
    ]
)
interface SignUpFeatureComponent : SignUpFeatureApi {

    fun signUpComponentFactory(): SignUpComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(signUpRouter: SignUpRouter): Builder

        fun withDependencies(deps: SignUpFeatureDependencies): Builder

        fun build(): SignUpFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface SignUpFeatureDependenciesComponent : SignUpFeatureDependencies
}