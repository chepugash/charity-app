package com.example.charityapp.di

import android.content.Context
import com.example.charityapp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @AppScope
    @Provides
    fun provideContext(application: App): Context {
        return application
    }
}