package com.example.common.di.modules

import android.content.Context
import com.example.common.core.ResourceManager
import com.example.common.core.config.AppProperties
import com.example.common.core.storage.Preferences
import com.example.common.data.storage.PreferencesImpl
import com.example.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    @ApplicationScope
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManager(context)
    }

    @Provides
    @ApplicationScope
    fun provideAppProperties(context: Context): AppProperties {
        return AppProperties(context)
    }

    @Provides
    @ApplicationScope
    fun providePreferences(context: Context): Preferences {
        return PreferencesImpl(context)
    }
}