package com.example.foundation_info.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.foundation_info.FoundationRouter
import com.example.foundation_info.presentation.di.FoundationComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        FoundationFeatureDependencies::class,
    ],
    modules = [
        FoundationFeatureModule::class
    ]
)
interface FoundationFeatureComponent {

    fun foundationComponentFactory(): FoundationComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(foundationRouter: FoundationRouter): Builder

        fun withDependencies(deps: FoundationFeatureDependencies): Builder

        fun build(): FoundationFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface FoundationFeatureDependenciesComponent: FoundationFeatureDependencies
}