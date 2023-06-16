package com.example.history.di

import com.example.common.di.FeatureApiHolder
import com.example.common.di.FeatureContainer
import com.example.history.HistoryRouter
import javax.inject.Inject

class HistoryFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val historyRouter: HistoryRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val historyFeatureDependencies =
            DaggerHistoryFeatureComponent_HistoryFeatureDependenciesComponent.builder()
                .commonApi(commonApi())
                .build()
        return DaggerHistoryFeatureComponent.builder()
            .withDependencies(historyFeatureDependencies)
            .router(historyRouter)
            .build()
    }
}