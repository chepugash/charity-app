package com.example.sign.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.sign.SignUpRouter
import com.example.sign.data.SignApi
import com.example.sign.presentation.di.SignInComponent
import com.example.sign.presentation.di.SignUpComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        SignFeatureDependencies::class,
    ],
    modules = [
        SignFeatureModule::class
    ]
)
interface SignFeatureComponent : SignApi {

    fun signUpComponentFactory(): SignUpComponent.Factory

    fun signInComponentFactory(): SignInComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(signUpRouter: SignUpRouter): Builder

        fun withDependencies(deps: SignFeatureDependencies): Builder

        fun build(): SignFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface SignFeatureDependenciesComponent : SignFeatureDependencies
}