package com.example.foundations.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.foundations.FoundationsRouter
import com.example.foundations.presentation.di.FoundationsComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        FoundationsFeatureDependencies::class,
    ],
    modules = [
        FoundationsFeatureModule::class
    ]
)
interface FoundationsFeatureComponent {

    fun foundationsComponentFactory(): FoundationsComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(foundationsRouter: FoundationsRouter): Builder

        fun withDependencies(deps: FoundationsFeatureDependencies): Builder

        fun build(): FoundationsFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface FoundationsFeatureDependenciesComponent: FoundationsFeatureDependencies
}