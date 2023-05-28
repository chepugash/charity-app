package com.example.sign.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.sign.SignRouter
import com.example.sign.presentation.nouser.di.NoUserComponent
import com.example.sign.presentation.signin.di.SignInComponent
import com.example.sign.presentation.signup.di.SignUpComponent
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
interface SignFeatureComponent {

    fun signUpComponentFactory(): SignUpComponent.Factory

    fun signInComponentFactory(): SignInComponent.Factory

    fun noUserComponentFactory(): NoUserComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(signRouter: SignRouter): Builder

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