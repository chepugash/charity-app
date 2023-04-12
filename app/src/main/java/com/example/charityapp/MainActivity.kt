package com.example.charityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.charityapp.databinding.ActivityMainBinding
import com.example.sign_up.presentation.SignUpFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
            .navController

    }
}