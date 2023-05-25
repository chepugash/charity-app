package com.example.categories.di

import com.example.categories.CategoriesRouter
import com.example.categories.presentation.di.CategoriesComponent
import com.example.common.di.CommonApi
import com.example.common.di.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        CategoriesFeatureDependencies::class,
    ],
    modules = [
        CategoriesFeatureModule::class
    ]
)
interface CategoriesFeatureComponent {

    fun categoriesComponentFactory(): CategoriesComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(categoriesRouter: CategoriesRouter): Builder

        fun withDependencies(deps: CategoriesFeatureDependencies): Builder

        fun build(): CategoriesFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface CategoriesFeatureDependenciesComponent: CategoriesFeatureDependencies
}