package com.example.charityapp.navigation

import android.content.Context
import androidx.navigation.NavController
import com.example.charityapp.MainActivity
import com.example.charityapp.R
import com.example.sign_up.SignUpRouter

class Navigator : SignUpRouter {

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

    override fun openMain(context: Context) {
        MainActivity.start(context)
    }
}