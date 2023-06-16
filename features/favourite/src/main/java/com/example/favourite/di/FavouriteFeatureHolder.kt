package com.example.favourite.di

import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.favourite.FavouriteRouter
import javax.inject.Inject

class FavouriteFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val favouriteRouter: FavouriteRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val favouriteFeatureDependencies =
            DaggerFavouriteFeatureComponent_FavouriteFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerFavouriteFeatureComponent.builder()
            .withDependencies(favouriteFeatureDependencies)
            .router(favouriteRouter)
            .build()
    }
}