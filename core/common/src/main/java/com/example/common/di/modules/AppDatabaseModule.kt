package com.example.common.di.modules

import android.content.Context
import androidx.room.Room
import com.example.common.data.storage.AppDatabase
import com.example.common.data.storage.dao.FoundationDao
import com.example.common.data.storage.dao.TransactionDao
import com.example.common.data.storage.dao.UserDao
import com.example.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseModule {

    @ApplicationScope
    @Provides
    fun provideDatabase(applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).build()

    @Provides
    fun provideUserDao(
        appDatabase: AppDatabase
    ): UserDao = appDatabase.getUserDao()

    @Provides
    fun provideFoundationDao(
        appDatabase: AppDatabase
    ): FoundationDao = appDatabase.getFoundationDao()

    @Provides
    fun provideTransactionDao(
        appDatabase: AppDatabase
    ): TransactionDao = appDatabase.getTransactionDao()
}
