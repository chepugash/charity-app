package com.example.favourite.di

import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import com.example.favourite.FavouriteRouter
import com.example.favourite.presentation.di.FavouriteComponent
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        FavouriteFeatureDependencies::class,
    ],
    modules = [
        FavouriteFeatureModule::class
    ]
)
interface FavouriteFeatureComponent {

    fun favouriteComponentFactory(): FavouriteComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(favouriteRouter: FavouriteRouter): Builder

        fun withDependencies(deps: FavouriteFeatureDependencies): Builder

        fun build(): FavouriteFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface FavouriteFeatureDependenciesComponent: FavouriteFeatureDependencies
}