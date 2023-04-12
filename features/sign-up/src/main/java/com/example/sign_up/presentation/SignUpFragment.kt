package com.example.sign_up.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.sign_up.R
import com.example.sign_up.databinding.FragmentSignUpBinding
import com.example.sign_up.di.SignUpComponentViewModel
import dagger.Lazy
import javax.inject.Inject

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private var binding: FragmentSignUpBinding? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<SignUpComponentViewModel>()
            .component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        binding?.run {
        }
    }
}