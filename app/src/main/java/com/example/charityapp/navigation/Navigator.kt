package com.example.charityapp.navigation

import android.content.Context
import androidx.navigation.NavController
import com.example.charityapp.MainActivity
import com.example.charityapp.R
import com.example.profile.ProfileRouter
import com.example.sign.SignRouter

class Navigator : SignRouter, ProfileRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun launchSignIn() {
        navController?.navigate(R.id.signInFragment)
    }

    override fun launchSignUp() {
        navController?.navigate(R.id.signUpFragment)
    }

    override fun launchProfile() {
        navController?.navigate(R.id.profileFragment)
    }

    override fun launchNameDialog() {
        navController?.navigate(R.id.nameDialogFragment)
    }

    override fun launchSignOutDialog() {
        navController?.navigate(R.id.signOutDialogFragment)
    }

    override fun openMain(context: Context) {
        MainActivity.start(context)
    }
}