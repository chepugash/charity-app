package com.example.sign_up.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sign_up.R
import com.example.sign_up.databinding.FragmentRegistrationBinding

class SignUpFragment : Fragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)

        binding?.run {

        }
    }
}