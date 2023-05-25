package com.example.profile.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.profile.ProfileRouter
import com.example.profile.presentation.di.ProfileComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        ProfileFeatureDependencies::class,
    ],
    modules = [
        ProfileFeatureModule::class
    ]
)
interface ProfileFeatureComponent {

    fun profileComponentFactory(): ProfileComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(profileRouter: ProfileRouter): Builder

        fun withDependencies(deps: ProfileFeatureDependencies): Builder

        fun build(): ProfileFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface ProfileFeatureDependenciesComponent: ProfileFeatureDependencies
}