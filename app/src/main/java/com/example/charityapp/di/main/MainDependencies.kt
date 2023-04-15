package com.example.charityapp.di.main

import com.example.charityapp.di.deps.ComponentDependencies
import com.example.charityapp.navigation.Navigator

interface MainDependencies : ComponentDependencies {

    fun navigator(): Navigator
}