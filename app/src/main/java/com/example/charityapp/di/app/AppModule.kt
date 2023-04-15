package com.example.charityapp.di.app

import android.content.Context
import com.example.charityapp.App
import com.example.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @ApplicationScope
    @Provides
    fun provideContext(application: App): Context {
        return application
    }
}