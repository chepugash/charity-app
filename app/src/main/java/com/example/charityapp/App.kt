package com.example.charityapp

import android.app.Application
import com.example.charityapp.di.app.AppComponent
import com.example.charityapp.di.deps.ComponentDependenciesProvider
import com.example.charityapp.di.deps.FeatureHolderManager
import com.example.charityapp.di.deps.HasComponentDependencies
import com.example.common.di.CommonApi
import com.example.common.di.FeatureContainer
import timber.log.Timber
import javax.inject.Inject

class App : Application(), FeatureContainer, HasComponentDependencies {

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var featureHolderManager: FeatureHolderManager

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        super.onCreate()

        appComponent = AppComponent.init(this)
        appComponent.inject(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun <T> getFeature(key: Class<*>): T {
        return featureHolderManager.getFeature<T>(key)!!
    }

    override fun releaseFeature(key: Class<*>) {
        featureHolderManager.releaseFeature(key)
    }

    override fun commonApi(): CommonApi {
        return appComponent
    }

}
