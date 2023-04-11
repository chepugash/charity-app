package com.example.charityapp.di

import android.app.Application
import com.example.sign_up.di.SignUpDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : SignUpDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule {

}

@Scope
annotation class AppScope