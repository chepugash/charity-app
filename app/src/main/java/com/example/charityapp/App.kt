package com.example.charityapp

import android.app.Application
import com.example.charityapp.di.AppComponent
import com.example.charityapp.di.DaggerAppComponent
import com.example.sign_up.di.SignUpDepsStore
import timber.log.Timber

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = AppComponent.init(this)
        appComponent.inject(this)
        SignUpDepsStore.deps = appComponent
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
