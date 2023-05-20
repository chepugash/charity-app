package com.example.categories.di

import com.example.categories.CategoriesRouter
import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import javax.inject.Inject

class CategoriesFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val categoriesRouter: CategoriesRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val categoriesFeatureDependencies =
            DaggerCategoriesFeatureComponent_CategoriesFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerCategoriesFeatureComponent.builder()
            .withDependencies(categoriesFeatureDependencies)
            .router(categoriesRouter)
            .build()
    }
}