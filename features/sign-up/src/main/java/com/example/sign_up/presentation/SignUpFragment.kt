package com.example.sign_up.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.sign_up.R
import com.example.sign_up.databinding.FragmentSignUpBinding
import com.example.sign_up.di.SignUpFeatureApi
import com.example.sign_up.di.SignUpFeatureComponent

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            btnSubmit.setOnClickListener {
            }
        }
    }

    override fun inject() {

        FeatureUtils.getFeature<SignUpFeatureComponent>(this, SignUpFeatureApi::class.java)
            .signUpComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {

    }

    override fun subscribe(viewModel: SignUpViewModel) {

    }


}