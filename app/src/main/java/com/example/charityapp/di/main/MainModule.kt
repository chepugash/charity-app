package com.example.charityapp.di.main

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.charityapp.MainViewModel
import com.example.common.di.viewmodel.ViewModelKey
import com.example.common.di.viewmodel.ViewModelModule
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class MainModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideViewModel() : ViewModel {
        return MainViewModel()
    }

    @Provides
    fun provideViewModelCreator(activity: AppCompatActivity, viewModelFactory: ViewModelProvider.Factory): MainViewModel {
        return ViewModelProvider(activity, viewModelFactory)[MainViewModel::class.java]
    }
}