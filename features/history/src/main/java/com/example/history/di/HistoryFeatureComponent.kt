package com.example.history.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.history.HistoryRouter
import com.example.history.presentation.di.HistoryComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        HistoryFeatureDependencies::class,
    ],
    modules = [
        HistoryFeatureModule::class
    ]
)
interface HistoryFeatureComponent {

    fun historyComponentFactory(): HistoryComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(historyRouter: HistoryRouter): Builder

        fun withDependencies(deps: HistoryFeatureDependencies): Builder

        fun build(): HistoryFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface HistoryFeatureDependenciesComponent: HistoryFeatureDependencies
}