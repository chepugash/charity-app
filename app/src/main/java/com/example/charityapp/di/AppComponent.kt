package com.example.charityapp.di

import android.app.Application
import com.example.charityapp.App
import com.example.sign_up.di.SignUpDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class, NavigationModule::class])]
interface AppComponent : SignUpDeps {

    companion object {

        fun init(application: App): AppComponent {
            return DaggerAppComponent
                .builder()
                .application(application)
                .build()
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}

@Scope
annotation class AppScope