package com.example.charityapp.di.app

import com.example.charityapp.App
import com.example.charityapp.di.deps.ComponentDependenciesModule
import com.example.charityapp.di.deps.ComponentHolderModule
import com.example.charityapp.di.main.MainDependencies
import com.example.common.di.CommonApi
import com.example.common.di.modules.CommonModule
import com.example.common.di.modules.NetworkModule
import com.example.common.di.scope.ApplicationScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        CommonModule::class,
        NetworkModule::class,
        NavigationModule::class,
        ComponentHolderModule::class,
        ComponentDependenciesModule::class,
        FeatureManagerModule::class
    ]
)
interface AppComponent : MainDependencies, CommonApi {
    override fun provideFirestore(): FirebaseFirestore
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