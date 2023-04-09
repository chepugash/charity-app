package com.example.charityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.charityapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthFragment())
                .commit()
        }
    }
}