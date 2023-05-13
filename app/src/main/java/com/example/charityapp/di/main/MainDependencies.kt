package com.example.charityapp.di.main

import com.example.charityapp.di.deps.ComponentDependencies
import com.example.charityapp.navigation.Navigator
import com.google.firebase.firestore.FirebaseFirestore

interface MainDependencies : ComponentDependencies {

    fun navigator(): Navigator
}